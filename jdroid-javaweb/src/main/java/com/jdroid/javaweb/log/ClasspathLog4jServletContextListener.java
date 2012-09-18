package com.jdroid.javaweb.log;

import java.io.FileNotFoundException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.helpers.LogLog;
import org.springframework.util.ClassUtils;
import org.springframework.util.Log4jConfigurer;

/**
 * Listener to load from the classpath the log4j configuration file.
 */
public class ClasspathLog4jServletContextListener implements ServletContextListener {
	
	private static final Log LOG = LogFactory.getLog(ClasspathLog4jServletContextListener.class);
	
	/**
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			// If the file log4j.deployment.xml is present in the classpath, it is used
			// else the file log4j.xml is used
			String log4jPath = "classpath:log4j.xml";
			if (ClassUtils.getDefaultClassLoader().getResource("log4j.xml") == null) {
				log4jPath = "classpath:log4j.deployment.xml";
			}
			Log4jConfigurer.initLogging(log4jPath);
			ClasspathLog4jServletContextListener.LOG.info("Starting Logging.");
		} catch (FileNotFoundException ex) {
			LogLog.error(ex.getMessage());
		}
	}
	
	/**
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ClasspathLog4jServletContextListener.LOG.info("Shutdown Logging.");
		Log4jConfigurer.shutdownLogging();
	}
}
