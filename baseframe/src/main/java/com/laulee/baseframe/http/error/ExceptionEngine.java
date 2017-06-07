package com.laulee.baseframe.http.error;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * 除自身接口异常的其他异常
 * Created by laulee on 17/6/5.
 */
public class ExceptionEngine {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ErrorException handleException( Throwable e ) {
        ErrorException ex;
        if( e instanceof HttpException ) {             //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ErrorException( e, ERROR.HTTP_ERROR );
            switch( httpException.code( ) ) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.message = "网络错误";  //均视为网络错误
                    break;
            }
            return ex;
        } else if( e instanceof RuntimeException ) {    //服务器返回的错误
            RuntimeException resultException = (RuntimeException) e;
            ex = new ErrorException( resultException, ERROR.RUNTIME_ERROR );
            ex.message = resultException.getMessage( );
            return ex;
        } else if( e instanceof JsonParseException || e instanceof JSONException || e instanceof
                ParseException ) {
            ex = new ErrorException( e, ERROR.PARSE_ERROR );
            ex.message = "数据解析错误";            //均视为解析错误
            return ex;
        } else if( e instanceof ConnectException ) {
            ex = new ErrorException( e, ERROR.NETWORD_ERROR );
            ex.message = "网络连接失败";  //均视为网络错误
            return ex;
        } else {
            ex = new ErrorException( e, ERROR.UNKNOWN );
            ex.message = "未知错误";          //未知错误
            return ex;
        }
    }
}
