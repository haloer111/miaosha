package com.gexiao.miaosha.exception;

/**
 * 业务异常
 * @author : gexiao
 * @since : 2019/11/19 14:19
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String msg){
        super(msg);
    }
}
