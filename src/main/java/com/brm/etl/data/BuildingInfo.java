package com.brm.etl.data;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@CsvRecord(separator = "\\|")
public class BuildingInfo {
	@DataField(pos = 1, trim = true)
	public String DONGCODE;
	@DataField(pos = 2, trim = true)
	public String SIDO;
	@DataField(pos = 3, trim = true)
	public String SIGUNGU;
	@DataField(pos = 4, trim = true)
	public String DONG;
	@DataField(pos = 5, trim = true)
	public String RI;
	@DataField(pos = 6, trim = true)
	public String ISMOUNTAIN;
	@DataField(pos = 7, trim = true)
	public String JIBUN1;
	@DataField(pos = 8, trim = true)
	public String JIBUN2;
	@DataField(pos = 9, trim = true)
	public String STREETCODE;
	@DataField(pos = 10, trim = true)
	public String STREET;
	@DataField(pos = 11, trim = true)
	public String ISUNDER;
	@DataField(pos = 12, trim = true)
	public String BUILDINGNUM1;
	@DataField(pos = 13, trim = true)
	public String BUILDINGNUM2;
	@DataField(pos = 14, trim = true)
	public String BUILDING;
	@DataField(pos = 15, trim = true)
	public String BUILDINGDETAIL;
	@DataField(pos = 16, trim = true)
	public String BUILDINGCODE;
	@DataField(pos = 17, trim = true)
	public String DONGSEQ;
	@DataField(pos = 18, trim = true)
	public String HAENGDONGCODE;
	@DataField(pos = 19, trim = true)
	public String HAENGDONG;
	@DataField(pos = 20, trim = true)
	public String ZIPCODE;
	@DataField(pos = 21, trim = true)
	public String ZIPSEQ;
	@DataField(pos = 22, trim = true)
	public String MASSDESTINATION;
	@DataField(pos = 23, trim = true)
	public String MOVEREASION;
	@DataField(pos = 24, trim = true)
	public String GOSIDATE;
	@DataField(pos = 25, trim = true)
	public String OLDADDRESS;
	@DataField(pos = 26, trim = true)
	public String SIGUNGUBUILDING;
	@DataField(pos = 27, trim = true)
	public String APT;
	@DataField(pos = 28, trim = true)
	public String BASICDESTRICTNUM; // 28 기초구역번호 CHAR(5)
	@DataField(pos = 29, trim = true)
	public String ISASIGNDETAILADDR; // 29 상세주소 부여여부 CHAR(1)
	@DataField(pos = 30, trim = true)
	public String REMARK1; // 30 비고1 CHAR(15)
	@DataField(pos = 31, trim = true)
	public String REMARK2; // 31 비고2 CHAR(15)

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}
}
