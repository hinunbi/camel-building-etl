package com.brm.etl.biz;

import java.sql.SQLException;

import com.brm.etl.data.BuildingInfo;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangeException;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CounterProcessor implements Processor {

	private static final Logger logger = LoggerFactory.getLogger(CounterProcessor.class);

	Integer successTotal = 0;
	Integer errorTotal = 0;

	@Override
	public void process(Exchange exchange) throws Exception {
		successTotal ++;
	}

	public void reset() {
		successTotal = 0;
		errorTotal = 0;
	}

	public void report() {
		logger.info("Success total : " + successTotal);
		logger.info("Error   total : " + errorTotal);
	}

	public void handleException(@Body BuildingInfo info, @ExchangeException SQLException e) {
		errorTotal++;
		logger.warn("ERROR >>> " + e.getMessage().trim());
	}
}
