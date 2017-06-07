package com.laulee.baseframe.http.error;

/**
 * Created by laulee on 17/6/5.
 */

/**
 * 服务器以及解析数据异常处理
 */
public class ErrorException extends Exception {

    public String message;
    private int code;

    public ErrorException( Throwable throwable, int code ) {
        super( throwable );
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
