package com.laulee.retrofit2.model;

import com.laulee.baseframe.http.callback.ModelCallBack;
import com.laulee.baseframe.rx.ObserveMapper;
import com.laulee.baseframe.rx.RxSubscriber;
import com.laulee.retrofit2.bean.entity.GankItemEntity;
import com.laulee.retrofit2.bean.entity.GankResult;
import com.laulee.retrofit2.http.HttpApis;
import com.laulee.retrofit2.presenter.contact.AndroidFragmentContact;

import java.util.List;

import rx.Subscription;

/**
 * Created by laulee on 16/12/27.
 */

public class AndroidFragmentModel implements AndroidFragmentContact.IModel {

    @Override
    public Subscription getAndroidImage( String tech, int num, int page,
            ModelCallBack<List<GankItemEntity>> callBack ) {
        Subscription subscription = HttpApis.getGankApis( )
                .getGankData( tech, num, page )
                .compose( ObserveMapper.<GankResult<List<GankItemEntity>>> rxSchedulerHelper( ) )
                .compose( ObserveMapper.<List<GankItemEntity>> transformer( ) )
                .subscribe( new RxSubscriber<List<GankItemEntity>>( callBack ) );
        return subscription;
    }
}
