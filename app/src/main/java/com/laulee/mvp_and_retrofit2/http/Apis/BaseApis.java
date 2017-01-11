package com.laulee.mvp_and_retrofit2.http.Apis;

import com.laulee.mvp_and_retrofit2.base.HttpResult;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by laulee on 16/12/25.
 */

public interface BaseApis {

    @GET("{url}")
    Observable<HttpResult<String>> getApiUrl( @Path("url") String url,
            @QueryMap Map<String, String> params );
}
