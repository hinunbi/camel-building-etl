package com.brm.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class InspectProcessor implements Processor {

	private static final Logger logger = LoggerFactory.getLogger(InspectProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		Message in = exchange.getIn();
		logger.info(">>>>>>>>>> " + in.toString());

	}

}
