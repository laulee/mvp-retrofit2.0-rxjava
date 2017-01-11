package com.laulee.mvp_and_retrofit2.presenter.contact;

import com.laulee.mvp_and_retrofit2.base.BasePrestenerInterface;
import com.laulee.mvp_and_retrofit2.base.BaseView;
import com.laulee.mvp_and_retrofit2.bean.params.GetApiParams;

/**
 * Created by laulee on 16/12/25.
 */

public class GetApiContact {

    public interface View extends BaseView {
        void setTextView( String apiUrl );
    }

    public interface Prestener extends BasePrestenerInterface<View> {
        void getApiMethod( GetApiParams getApiParams );
    }

}
