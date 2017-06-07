package com.laulee.retrofit2.presenter;

import com.laulee.baseframe.mvp.presenter.RxPresenter;
import com.laulee.retrofit2.bean.entity.GankItemEntity;
import com.laulee.retrofit2.http.callback.CallBack;
import com.laulee.retrofit2.model.AndroidFragmentModel;
import com.laulee.retrofit2.presenter.contact.AndroidFragmentContact;

import java.util.List;

import rx.Subscription;

/**
 * Created by laulee on 17/2/27.
 */

public class AndroidFragmentPresenter
        extends RxPresenter<AndroidFragmentContact.IView, AndroidFragmentModel>
        implements AndroidFragmentContact.IPresenter {

    public AndroidFragmentPresenter() {
        mModel = new AndroidFragmentModel( );
    }

    @Override
    public void getAndroidImage( String tech, int num, int page ) {

        Subscription subscription = mModel
                .getAndroidImage( tech, num, page, new CallBack<List<GankItemEntity>>( ) {
                    @Override
                    public void onSuccess( List<GankItemEntity> gankItemEntities ) {
                        showContent( gankItemEntities );
                    }

                    @Override
                    public void onError( String message ) {
                        showError( message );
                    }
                } );
        addSubscrebe( subscription );
    }

    private void showError( String message ) {
        mView.showError( message );
    }

    private void showContent( List<GankItemEntity> gankItemEntities ) {
        mView.showContent( gankItemEntities );
    }
}
