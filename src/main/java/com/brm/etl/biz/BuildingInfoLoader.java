package com.brm.etl.biz;

import java.sql.SQLException;
import java.util.Date;

import com.brm.etl.Comtsmsdh;
import com.brm.etl.data.BuildingInfo;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BuildingInfoLoader {

	private static final Logger logger = LoggerFactory.getLogger(BuildingInfoLoader.class);

	@Autowired
	SqlSession sqlSession;

	@Produce(uri = "direct:sms")
	ProducerTemplate producer;

	Integer updateTotal = 0;
	Integer insertTotal = 0;
	Integer deleteTotal = 0;
	Integer total = 0;


	public void reset() {
		updateTotal = 0;
		insertTotal = 0;
		deleteTotal = 0;
		total = 0;
	}

	public void load(BuildingInfo buildingInfo) throws SQLException {

		Integer cnt;

		total++;

		// 최초 로드(전체 로드) : 멱등 수신자 패턴 적용
		if (buildingInfo.MOVEREASION == null || buildingInfo.MOVEREASION.equals("")) {

			cnt = sqlSession.selectOne("buildingInfo.selectCount", buildingInfo);
			if (cnt == 0) {
				sqlSession.insert("buildingInfo.insert", buildingInfo);
				insertTotal++;
			}
		}
		// 변경분 로드
		else {
			switch (buildingInfo.MOVEREASION) {
			case "31": // insert 도로명주소 부여
			case "73": // insert 건물군 내 신규 건물 추가
				cnt = sqlSession.selectOne("buildingInfo.selectCount", buildingInfo);
				if (cnt == 0) {
					sqlSession.insert("buildingInfo.insert", buildingInfo);
					insertTotal++;
				}
				break;
			case "34": // 도로명주소 변동
				cnt = sqlSession.selectOne("buildingInfo.selectCount", buildingInfo);
				if (cnt > 0) {
					sqlSession.update("buildingInfo.update", buildingInfo);
					updateTotal++;
				}
				break;
			case "63": // 도로명주소 폐지
			case "72": // 건물군 내 일부 건물 폐지
				cnt = sqlSession.selectOne("buildingInfo.selectCount", buildingInfo);
				if (cnt > 0) {
					sqlSession.delete("buildingInfo.delete", buildingInfo);
					deleteTotal++;
				}
				break;
			}
		}
	}

	public void report() {

		logger.info("Extract total : " + total);
		logger.info("Insert  total : " + insertTotal);
		logger.info("Update  total : " + updateTotal);
		logger.info("Delete  total : " + deleteTotal);

		// SMS 전송
		Comtsmsdh comtsmsdh = new Comtsmsdh();
		comtsmsdh.setSPHONE1("");
		comtsmsdh.setSPHONE2("1588");
		comtsmsdh.setSPHONE3("1533");
		comtsmsdh.setSENDNAME("도로명배치");
		comtsmsdh.setRPHONE1("010");
		comtsmsdh.setRPHONE2("7777");
		comtsmsdh.setRPHONE3("1234");
		comtsmsdh.setRECVNAME("홍길동");
		Date today = new Date();
		String nowDate = DateFormatUtils.format(today, "yyyyMMdd-HHmmss");
		String smsMsg = "[새주소배치]정상종료. 일자:" + nowDate + ".작업건수:" + total;
		logger.info("SMS : {}", smsMsg);
		// 주키 정보 입력 등
		comtsmsdh.setSENDID("barunmo");
		String inDate = DateFormatUtils.format(today, "yyyyMMdd");
		String inTime = DateFormatUtils.format(today, "HHmmss");
		comtsmsdh.setINDATE(inDate);
		comtsmsdh.setINTIME(inTime);
		comtsmsdh.setMSG(smsMsg);
		comtsmsdh.setRDATE("00000000");
		comtsmsdh.setRTIME("000000");
		comtsmsdh.setRESULT("0");
		comtsmsdh.setC_RESULT("A");
		comtsmsdh.setMEMBER(0L);
		comtsmsdh.setKIND("S");
		// SMS 전송 라우터 호출
		producer.sendBody(comtsmsdh);
	}
}
