package com.goodcitizens.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogUtils {

    public static void logInfo(Logger logger, LogLevel level, String textLog) {
        if (logger.isInfoEnabled() && level != null && textLog != null) {
            String msg = level.getLevel().concat(", ").concat(textLog);
            logger.info(msg);
        }
    }

    public static void logError(Logger logger, LogLevel level, String textLog) {
        if (logger.isEnabledFor(Level.ERROR)  && level != null && textLog != null) {
            String msg = level.getLevel().concat(", ").concat(textLog);
            logger.error(msg);
        }
    }

    public static void logDebug(Logger logger, LogLevel level, Object textLog) {
        if (logger.isDebugEnabled() && level != null && textLog != null) {
            String msg = level.getLevel().concat(", ").concat(textLog.toString());
            logger.debug(msg);
        }
    }


    public static void logWarning(Logger logger, LogLevel level, String textLog) {
        if (logger.isEnabledFor(Level.WARN) && level != null && textLog != null) {
            String msg = level.getLevel().concat(", ").concat(textLog);
            logger.warn(msg);
        }
    }

    public static void logTrace(Logger logger, LogLevel level, String textLog) {
        if (logger.isTraceEnabled() && level != null && textLog != null) {
            String msg = level.getLevel().concat(", ").concat(textLog);
            logger.trace(msg);
        }
    }

}
