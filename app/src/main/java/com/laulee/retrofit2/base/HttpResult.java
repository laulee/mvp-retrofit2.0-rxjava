package com.laulee.retrofit2.base;

/**
 * Created by laulee on 16/12/25.
 */

public class HttpResult<T> {

    private String source;
    private String message;

    private int code;

    private T value;

    public String getSource() {
        return source;
    }

    public T getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
