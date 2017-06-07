package com.laulee.retrofit2.presenter.contact;

import com.laulee.baseframe.http.callback.ModelCallBack;
import com.laulee.baseframe.mvp.model.IBaseModel;
import com.laulee.baseframe.mvp.presenter.IBasePresenter;
import com.laulee.baseframe.mvp.view.IBaseView;
import com.laulee.retrofit2.bean.entity.GankItemEntity;

import java.util.List;

import rx.Subscription;

/**
 * Created by laulee on 17/2/27.
 */

public class AndroidFragmentContact {

    public interface IView extends IBaseView {
        void showContent( List<GankItemEntity> gankItemEntities );

        void showError( String message );

        void showGirlImage( String url );
    }

    public interface IModel extends IBaseModel {
        Subscription getAndroidImage( String tech, int num, int page,
                ModelCallBack<List<GankItemEntity>> callBack );
    }


    public interface IPresenter extends IBasePresenter<IView> {
        void getAndroidImage( String tech, int num, int page );
    }
}
