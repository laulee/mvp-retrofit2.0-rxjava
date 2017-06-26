package com.laulee.baseframe.bean;

/**
 * 返回数据的父类
 * Created by laulee on 17/6/7.
 */

public abstract class ResponseBean<T> {

    //http 响应码 code定义成功失败
    public abstract int getCode();

    //http 部分是传递boolean类型定义成功失败
    public abstract boolean isError();

    public abstract String getMessage();

    public abstract T getValues();
}
