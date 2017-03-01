package com.laulee.retrofit2.presenter.contact;

import com.laulee.retrofit2.base.BaseView;
import com.laulee.retrofit2.bean.entity.GankItemEntity;

import java.util.List;

/**
 * Created by laulee on 17/2/27.
 */

public class AndroidFragmentContact {

    public interface AndroidFragmentView extends BaseView{
        void showContent( List<GankItemEntity> gankItemEntities );

        void showError( String message );

        void showGirlImage( String url );
    }

}
