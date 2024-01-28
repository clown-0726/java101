package com.lilu.misc.logging;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


public class JCLBinding {

    /**
     * 绑定日志实现的入口
     **/
    //// 直接使用 JCL，会自动找实现
    //private static final Log logger1 = LogFactory.getLog(JCLBinding.class);
    //// 使用 JCL 的实现 Log4j2
    //private static final Logger logger2 = LogManager.getLogger(JCLBinding.class);
    //
    //// 这样写，其子类不需要进行 logger 的声明了，可以直接使用 logger
    //protected final Log logger3 = LogFactory.getLog(getClass());
    //
    //public static void main(String[] args) {
    //    // JCL 不支持占位符
    //    logger1.info("I am logger");
    //    logger1.error("I am logger");
    //    // Log4j2 支持占位符
    //    logger2.info("I am logger [{}]", "abc");
    //    logger2.error("I am logger [{}]", "abc");
    //}
}
