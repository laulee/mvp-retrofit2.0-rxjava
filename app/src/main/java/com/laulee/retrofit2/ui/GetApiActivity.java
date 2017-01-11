package com.laulee.retrofit2.ui;

import android.widget.TextView;

import com.laulee.retrofit2.R;
import com.laulee.retrofit2.base.BaseActivity;
import com.laulee.retrofit2.bean.params.GetApiParams;
import com.laulee.retrofit2.presenter.GetApiPrestener;
import com.laulee.retrofit2.presenter.contact.GetApiContact;

/**
 * Created by laulee on 16/12/25.
 */

public class GetApiActivity extends BaseActivity<GetApiPrestener> implements GetApiContact.View {

    TextView tvApiUrl;
    GetApiParams getApiParams;

    @Override
    protected void initView() {
        tvApiUrl = (TextView) findViewById( R.id.tvApiUrl );
    }

    @Override
    protected void initParams() {
        getApiParams = new GetApiParams( this );
        mPresenter.getApiMethod( getApiParams );
    }

    @Override
    protected int setContentViewId() {
        return R.layout.get_api_layout;
    }

    @Override
    protected GetApiPrestener createPresenter() {
        return new GetApiPrestener( );
    }

    @Override
    public void setTextView( String apiUrl ) {
        tvApiUrl.setText( apiUrl );
    }
}
