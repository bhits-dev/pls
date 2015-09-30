package com.feisystems.provider.web.controller.instrumentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Class LoggerCheckController.
 */
@Controller
@RequestMapping("/instrumentation") 
public class LoggerCheckController {
	
	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** The logger instrumentation key. */
	private String loggerInstrumentationKey;
	
	@Autowired
	public LoggerCheckController(@Value("${instrumentationKey}") String loggerInstrumentationKey) {
		this.loggerInstrumentationKey = loggerInstrumentationKey;
	}
	
	/**
	 * Test whether the Provider logging work.
	 *
	 * @param key the key
	 * @return the string
	 */
	@RequestMapping(value = "/loggerCheck", method = RequestMethod.GET, produces = "text/html")
	public @ResponseBody String check(@RequestParam(value="instrumentationKey")String key) {
		
		if (!loggerInstrumentationKey.equals(key)) {
            return "<h2>You are not authorized to access this page.</h2>";
        }
		
		// The following loops are used to make ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter be alive to scan logback configuration changes if logback is used
		// You may request this url several times to activate the configuration changes
		for (int i = 0 ; i < 200; i++) {
			logger.trace("trace: just a test.");
		}

		logger.debug("debug: just a test.");
		logger.info("info: just a test.");
		logger.warn("warn: just a test.");
		logger.error("error: just a test.");
		
		String loggerLevel;
		
		if (logger.isDebugEnabled()) {
			loggerLevel = "Debug";
		} else if (logger.isInfoEnabled()) {
			loggerLevel = "Info";
		} else if (logger.isWarnEnabled()) {
			loggerLevel = "Warn";
		} else {
			loggerLevel = "Error";
		} 
		
		return "<p>This page is used for logging test. And if logback is the logging library, you can request this page serveral times to activate logback configuration changes.</p><hr/>"
				+"<h3>Logger named ["+logger.getName()+ "]</h3>\r\n"+ "<h3>Logger Level is ["+ loggerLevel+ "]</h3>";
	}
}

