package com.wondernect.elements.log;

import com.wondernect.elements.log.context.DefaultLoggerContext;
import com.wondernect.elements.log.context.LoggerContext;
import com.wondernect.elements.log.em.LogLevel;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MarkerIgnoringBase;

import java.io.Serializable;
import java.util.Map;

public class Logger extends MarkerIgnoringBase implements Serializable {

	private static final long serialVersionUID = -7001943531059890186L;

	private org.slf4j.Logger logger;

	private static final org.slf4j.Logger wondernectLogger = LoggerFactory.getLogger("WondernectLogger");

	/** * The logger context name. */
	private static String loggerContextName;

	/** * The logger context. */
	private static LoggerContext loggerContext;

	static {
		initialize();
	}

	/**
	 * 私有构造函数
	 */
	private Logger(String name) {
		logger = LoggerFactory.getLogger(name);
	}

	/**
	 * 根据名称获取Logger实例
	 */
	public static Logger getLogger(String name) {
		return new Logger(name);
	}

	/**
	 * 根据Class获取Logger实例
	 */
	public static Logger getLogger(Class<?> clazz) {
		return new Logger(clazz.getName());
	}

	/**
	 * Changes the preferred logger context.
	 */
	public static void setLoggerContextName(String loggerContextName) {
		Logger.loggerContextName = loggerContextName;
		initialize();
	}

	/**
	 * Initialize.
	 */
	private static void initialize() {
		if ((loggerContextName == null) || "".equals(loggerContextName)) {
			// Set default
			loggerContext = new DefaultLoggerContext();
			return;
		}
		// Try to load a custom logger context
		try {
			Class<?> clazz = Class.forName(loggerContextName);
			loggerContext = (LoggerContext) clazz.newInstance();
		} catch (Exception ex) {
			throw new IllegalStateException("Initialize logger context error: " + ex.getMessage());
		}
	}

	/**
	 * 操作日志写入接口
	 */
	public void writeLog(LogLevel loggerLevel, Map<String, String> extendTablePropertyValues, String message) {
		loggerContext.constructLogTablePropertyValues(loggerLevel, extendTablePropertyValues);
		writeLog(loggerLevel, message);
	}

	/**
	 * 操作日志写入接口
	 */
	public void writeLog(LogLevel loggerLevel, Map<String, String> extendTablePropertyValues, String message, Object... messageArguments) {
		loggerContext.constructLogTablePropertyValues(loggerLevel, extendTablePropertyValues);
		writeLog(loggerLevel, message, messageArguments);
	}

	/**
	 * 操作日志写入接口
	 */
	public void writeLog(LogLevel loggerLevel, Map<String, String> extendTablePropertyValues, String message, Throwable messageThrowable) {
		loggerContext.constructLogTablePropertyValues(loggerLevel, extendTablePropertyValues);
		writeLog(loggerLevel, message, messageThrowable);
	}

	/**
	 * 具体操作日志写入
	 */
	private void writeLog(LogLevel level, String message) {
		switch (level) {
			case DEBUG:
				wondernectLogger.debug(message);
				break;
			case INFO:
				wondernectLogger.info(message);
				break;
			case WARN:
				wondernectLogger.warn(message);
				break;
			case ERROR:
				wondernectLogger.error(message);
				break;
		}
	}

	/**
	 * 具体操作日志写入
	 */
	private void writeLog(LogLevel level, String message, Object[] messageArrayOfObject) {
		switch (level) {
			case DEBUG:
				wondernectLogger.debug(message, messageArrayOfObject);
				break;
			case INFO:
				wondernectLogger.info(message, messageArrayOfObject);
				break;
			case WARN:
				wondernectLogger.warn(message, messageArrayOfObject);
				break;
			case ERROR:
				wondernectLogger.error(message, messageArrayOfObject);
				break;
		}
	}

	/**
	 * 具体操作日志写入
	 */
	private void writeLog(LogLevel level, String message, Throwable messageThrowable) {
		switch (level) {
			case DEBUG:
				wondernectLogger.debug(message, messageThrowable);
				break;
			case INFO:
				wondernectLogger.info(message, messageThrowable);
				break;
			case WARN:
				wondernectLogger.warn(message, messageThrowable);
				break;
			case ERROR:
				wondernectLogger.error(message, messageThrowable);
				break;
		}
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	@Override
	public void trace(String paramString) {
		logger.trace(paramString);
	}

	@Override
	public void trace(String paramString, Object paramObject) {
		logger.trace(paramString, paramObject);
	}

	@Override
	public void trace(String paramString, Object paramObject1, Object paramObject2) {
		logger.trace(paramString, paramObject1, paramObject2);
	}

	@Override
	public void trace(String paramString, Object[] paramArrayOfObject) {
		logger.trace(paramString, paramArrayOfObject);
	}

	@Override
	public void trace(String paramString, Throwable paramThrowable) {
		logger.trace(paramString, paramThrowable);
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public void debug(String paramString) {
		logger.debug(paramString);
	}

	@Override
	public void debug(String paramString, Object paramObject) {
		logger.debug(paramString, paramObject);
	}

	@Override
	public void debug(String paramString, Object paramObject1, Object paramObject2) {
		logger.debug(paramString, paramObject1, paramObject2);
	}

	@Override
	public void debug(String paramString, Object[] paramArrayOfObject) {
		logger.debug(paramString, paramArrayOfObject);
	}

	@Override
	public void debug(String paramString, Throwable paramThrowable) {
		logger.debug(paramString, paramThrowable);
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public void info(String paramString) {
		logger.info(paramString);
	}

	@Override
	public void info(String paramString, Object paramObject) {
		logger.info(paramString, paramObject);
	}

	@Override
	public void info(String paramString, Object paramObject1, Object paramObject2) {
		logger.info(paramString, paramObject1, paramObject2);
	}

	@Override
	public void info(String paramString, Object[] paramArrayOfObject) {
		logger.info(paramString, paramArrayOfObject);
	}

	@Override
	public void info(String paramString, Throwable paramThrowable) {
		logger.info(paramString, paramThrowable);
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public void warn(String paramString) {
		logger.warn(paramString);
	}

	@Override
	public void warn(String paramString, Object paramObject) {
		logger.warn(paramString, paramObject);
	}

	@Override
	public void warn(String paramString, Object[] paramArrayOfObject) {
		logger.warn(paramString, paramArrayOfObject);
	}

	@Override
	public void warn(String paramString, Object paramObject1, Object paramObject2) {
		logger.warn(paramString, paramObject1, paramObject2);
	}

	@Override
	public void warn(String paramString, Throwable paramThrowable) {
		logger.warn(paramString, paramThrowable);
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	@Override
	public void error(String paramString) {
		logger.error(paramString);
	}

	@Override
	public void error(String paramString, Object paramObject) {
		logger.error(paramString, paramObject);
	}

	@Override
	public void error(String paramString, Object paramObject1, Object paramObject2) {
		logger.error(paramString, paramObject1, paramObject2);
	}

	@Override
	public void error(String paramString, Object[] paramArrayOfObject) {
		logger.error(paramString, paramArrayOfObject);
	}

	@Override
	public void error(String paramString, Throwable paramThrowable) {
		logger.error(paramString, paramThrowable);
	}
}
