package com.laulee.retrofit2.http.Apis;

import com.laulee.retrofit2.bean.entity.GankItemEntity;
import com.laulee.retrofit2.bean.entity.GankResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by laulee on 17/2/28.
 */

public interface GankApis {

    /**
     * 技术文章列表
     */
    @GET("data/{tech}/{num}/{page}")
    Observable<GankResult<List<GankItemEntity>>> getGankData( @Path("tech") String tech,
            @Path("num") int num, @Path("page") int page );

    /**
     * 随机妹纸图
     */
    @GET("random/data/福利/{num}")
    Observable<GankResult<List<GankItemEntity>>> getRandomGirl(@Path("num") int num);
}
