package io.owen.enum_generator;

import io.owen.enum_generator.enums.EnumStyle;

import java.lang.annotation.*;

/**
 * Created by owen_q on 2018. 8. 31..
 */


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface ToJS {

    String name() default "";

    String description() default "";

    boolean insertDescription() default true;

    EnumStyle style() default EnumStyle.ES6;
}
