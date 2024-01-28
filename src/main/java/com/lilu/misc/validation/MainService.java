package com.lilu.misc.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class MainService {
    /**
     * 验证输入参数
     *
     * @param args
     * @throws NoSuchMethodException
     */
    //public static void main(String[] args) throws NoSuchMethodException {
    //    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    //    // 获取校验执行器
    //    ExecutableValidator executableValidator = validator.forExecutables();
    //    // 待验证对象
    //    UserInfoService service = new UserInfoService();
    //    // 待验证方法
    //    Method method = service.getClass().getMethod("setUserInfo", UserInfo.class);
    //    // 方法输入参数
    //    Object[] paramObjects = new Object[]{new UserInfo()};
    //    // 对方法的输入参数进行校验
    //    Set<ConstraintViolation<UserInfoService>> otherSet = executableValidator.validateParameters(service, method, paramObjects);
    //    otherSet.forEach(item -> {
    //        System.out.println(item.getMessage());
    //    });
    //}

    /**
     * 验证返回参数
     *
     * @param args
     * @throws NoSuchMethodException
     */
    //public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    //    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    //    // 获取校验执行器
    //    ExecutableValidator executableValidator = validator.forExecutables();
    //    // 待验证对象
    //    UserInfoService service = new UserInfoService();
    //    // 待验证方法
    //    Method method = service.getClass().getMethod("getUserInfo");
    //    // 方法输入参数
    //    Object returnValue = method.invoke(service);
    //    // 对方法的输入参数进行校验
    //    Set<ConstraintViolation<UserInfoService>> otherSet = executableValidator.validateReturnValue(service, method, returnValue);
    //    otherSet.forEach(item -> {
    //        System.out.println(item.getMessage());
    //    });
    //}

    /**
     * 验证返回参数
     *
     * @param args
     * @throws NoSuchMethodException
     */
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        // 获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();
        // 待验证对象
        UserInfoService service = new UserInfoService();
        // 待验证方法
        Constructor constructor = service.getClass().getConstructor(UserInfo.class);
        // 方法输入参数
        Object[] paramObjects = new Object[]{new UserInfo()};
        // 对方法的输入参数进行校验
        Set<ConstraintViolation<UserInfoService>> otherSet = executableValidator.validateConstructorParameters(constructor, paramObjects);
        otherSet.forEach(item -> {
            System.out.println(item.getMessage());
        });
    }
}
