package com.lilu.misc.logging;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jBinding {
    private static final Logger logger = LoggerFactory.getLogger(Slf4jBinding.class);

    private static void printLogbackStatus() {
        // 打印 Logback 的日志状态
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(loggerContext);
    }

    public static void main(String[] args) {
        if (logger.isInfoEnabled()) {
            logger.info("I am logger [{}]", "abc");
        }

        printLogbackStatus();
    }
}
