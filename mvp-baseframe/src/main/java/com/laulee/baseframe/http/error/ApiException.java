package com.laulee.baseframe.http.error;

/**
 * Created by laulee on 17/6/2.
 */

/**
 * 自定义接口异常处理
 */
public class ApiException extends Exception {
    private String message;
    private int code;

    public ApiException( int code, String message ) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
