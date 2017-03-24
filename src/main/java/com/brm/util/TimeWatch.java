package com.brm.util;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TimeWatch {

	private static final Logger logger = LoggerFactory.getLogger(TimeWatch.class);

	private long startTime;

	public void start(Exchange exchange) {
		startTime = System.currentTimeMillis();
	}

	public void stop(Exchange exchange) {
		// Message in = exchange.getIn();
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		//in.setHeader(TR_TIME, Long.valueOf(elapsedTime));
		logger.info(">>> {} msec on Route[{}]", elapsedTime, exchange.getFromRouteId());
	}
}