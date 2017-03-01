package com.laulee.retrofit2.presenter;

import com.laulee.retrofit2.base.BasePrestener;
import com.laulee.retrofit2.bean.entity.GankItemEntity;
import com.laulee.retrofit2.bean.entity.GankResult;
import com.laulee.retrofit2.http.Apis.Apis;
import com.laulee.retrofit2.http.Apis.GankApis;
import com.laulee.retrofit2.http.ObservableHelper;
import com.laulee.retrofit2.http.subscriber.ProgressSubscriber;
import com.laulee.retrofit2.presenter.contact.AndroidFragmentContact;

import java.util.List;

import rx.Observable;

/**
 * Created by laulee on 17/2/27.
 */

public class AndroidFragmentPresenter
        extends BasePrestener<AndroidFragmentContact.AndroidFragmentView> {

    public void getImageData() {
        Observable<GankResult<List<GankItemEntity>>> observable = Apis.getInstance( ).getRetrofit( )
                .create( GankApis.class ).getGankData( "Android", 20, 1 );
        ObservableHelper.getInstance( )
                .toSubscriber( observable, new ProgressSubscriber<List<GankItemEntity>>( ) {
                    @Override
                    protected void _onError( String message ) {
                        mView.showError( message );
                    }

                    @Override
                    protected void _onNext( List<GankItemEntity> gankItemEntities ) {
                        mView.showContent( gankItemEntities );
                    }
                } );
    }

    public void getGankData() {
        Observable<GankResult<List<GankItemEntity>>> observable = Apis.getInstance( ).getRetrofit( )
                .create( GankApis.class ).getRandomGirl( 10 );
        ObservableHelper.getInstance( )
                .toSubscriber( observable, new ProgressSubscriber<List<GankItemEntity>>( ) {
                    @Override
                    protected void _onError( String message ) {
                        mView.showError( message );
                    }

                    @Override
                    protected void _onNext( List<GankItemEntity> gankItemEntities ) {
                        mView.showGirlImage(gankItemEntities.get( 0 ).getUrl());
                    }
                } );
    }
}
