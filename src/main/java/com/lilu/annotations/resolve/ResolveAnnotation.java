package com.lilu.annotations.resolve;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ResolveAnnotation {
    String value();
}
