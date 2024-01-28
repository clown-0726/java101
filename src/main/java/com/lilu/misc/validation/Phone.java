package com.lilu.misc.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD}) // 注解的作用目标
@Retention(RetentionPolicy.RUNTIME) // 注解的保留策略
@Constraint(validatedBy = PhoneValidator.class)
public @interface Phone {
    String message() default "手机号验证错误";

    // 约束注解在验证时所属的组别
    Class<?>[] groups() default {};

    // 约束注解的有效负载
    Class<? extends Payload>[] payload() default {};
}
