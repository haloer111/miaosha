package com.gexiao.miaosha.exception;

import com.gexiao.miaosha.common.ResultEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常拦截器
 *
 * @author : gexiao
 * @since : 2019/11/19 11:55
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * jsr303校验参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object MethodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        List<Map> maps = new ArrayList<>();
        for (FieldError error : errors) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", error.getDefaultMessage());
            map.put("field", error.getField());
            maps.add(map);
        }
        return ResultEntity.failure("非法参数，请求错误",maps);
    }

    /**
     * 自定义业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public Object BusinessExceptionHandler(BusinessException e) {
        e.printStackTrace();
        return ResultEntity.failure(e.getMessage());
    }
}
