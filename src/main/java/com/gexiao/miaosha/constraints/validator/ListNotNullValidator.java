package com.gexiao.miaosha.constraints.validator;

import com.gexiao.miaosha.constraints.ListNotNull;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author : gexiao
 * @since : 2019/11/19 11:30
 */
public class ListNotNullValidator implements ConstraintValidator<ListNotNull, List> {

    @Override
    public boolean isValid(List value, ConstraintValidatorContext context) {
        for (Object o : value) {
            if (o == null) {
                return false;
            }
            if (o instanceof String) {
                if (StringUtils.isBlank((String) o)) {
                    return false;
                }
            }
        }
        return true;
    }
}
