package com.laulee.mvp_and_retrofit2.http.Apis;

import com.laulee.mvp_and_retrofit2.base.HttpResult;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by laulee on 16/12/26.
 */

public interface SystemApis {

    @GET("system")
    Observable<HttpResult<String>> getBaseApiUrl( @QueryMap Map<String, String> params );
}
