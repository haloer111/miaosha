package com.gexiao.miaosha.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

/**
 * @author : gexiao
 * @since : 2019/11/15 9:09
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultEntity<T> {
    private int code;
    private String msg;
    private T data;

    /**
     * 成功
     * @param data
     * @return
     */
    public static <T> ResultEntity success(T data) {
        return new ResultEntity(MessageCode.SUCCESS, "操作成功", data);
    }

    /**
     * 成功
     * @param data
     * @param msg
     * @return
     */
    public static <T> ResultEntity success(T data, String msg) {
        return new ResultEntity(MessageCode.SUCCESS, msg, data);
    }

    /**
     * 成功
     * @return
     */
    public static <T> ResultEntity success() {
        return new ResultEntity(MessageCode.SUCCESS, "操作成功", null);
    }

    /**
     * 失败
     * @param msg
     * @return
     */
    public static <T> ResultEntity failure(String msg) {
        return new ResultEntity(MessageCode.FAILURE, msg, null);
    }

    /**
     * 失败
     * @param e
     * @return
     */
    public static <T> ResultEntity failure(Exception e) {
        String errorMsg = Optional.ofNullable(e.getCause().toString()).orElseGet(() -> e.getCause().getMessage());
        return new ResultEntity(MessageCode.FAILURE, errorMsg, null);
    }


    public ResultEntity(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    interface MessageCode {
        int SUCCESS = 0;
        int FAILURE = -1;
    }
}
