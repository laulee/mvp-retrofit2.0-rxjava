package com.laulee.retrofit2.presenter.contact;

import com.laulee.retrofit2.base.BaseView;
import com.laulee.retrofit2.base.IBasePrestener;
import com.laulee.retrofit2.bean.params.GetApiParams;

/**
 * Created by laulee on 16/12/25.
 */

public class GetApiContact {

    public interface View extends BaseView {
        void setTextView( String apiUrl );
    }

    public interface Prestener extends IBasePrestener<View> {
        void getApiMethod( GetApiParams getApiParams );
    }

}
