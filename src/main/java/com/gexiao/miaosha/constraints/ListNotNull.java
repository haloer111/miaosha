package com.gexiao.miaosha.constraints;

import com.gexiao.miaosha.constraints.validator.ListNotNullValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author : gexiao
 * @since : 2019/11/19 11:29
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ListNotNullValidator.class})
public @interface ListNotNull {

    String message() default "集合内元素不能为空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@link javax.validation.constraints.NotNull} annotations on the same element.
     *
     * @see javax.validation.constraints.NotNull
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        javax.validation.constraints.NotNull[] value();
    }
}
