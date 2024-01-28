package com.lilu.misc.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("Zhangsan");
        userInfo.setPassword("123456");
        userInfo.setEmail("123@163.com");

        Set<ConstraintViolation<UserInfo>> validationResultSet = null;
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        validationResultSet = validator.validate(userInfo);
        validationResultSet.forEach(item -> {
            System.out.println(item.getMessage());
        });
    }
}
