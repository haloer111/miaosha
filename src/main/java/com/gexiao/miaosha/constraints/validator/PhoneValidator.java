package com.gexiao.miaosha.constraints.validator;

import com.gexiao.miaosha.constraints.Phone;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author : gexiao
 * @since : 2019/11/19 11:30
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private final static String PHONE_PATTERN = "1\\d{10}";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return false;
        } else {
            //校验是否是以1开头，并且是11位
            return Pattern.compile(PHONE_PATTERN).matcher(value).matches();
        }
    }
}
