package com.laulee.retrofit2.presenter;

import com.laulee.retrofit2.base.BasePrestener;
import com.laulee.retrofit2.base.HttpResult;
import com.laulee.retrofit2.bean.params.GetApiParams;
import com.laulee.retrofit2.http.Apis.Apis;
import com.laulee.retrofit2.http.Apis.SystemApis;
import com.laulee.retrofit2.http.ObservableHelper;
import com.laulee.retrofit2.http.subscriber.ProgressSubscriber;
import com.laulee.retrofit2.presenter.contact.GetApiContact;
import com.laulee.retrofit2.utils.LogUtil;

import rx.Observable;

/**
 * Created by laulee on 16/12/25.
 */

public class GetApiPrestener extends BasePrestener<GetApiContact.View>
        implements GetApiContact.Prestener {

//    private GetApiSourceImpl getApiSource;

    public GetApiPrestener() {
//        this.getApiSource = new GetApiSourceImpl( );
    }

    @Override
    public void getApiMethod( final GetApiParams getApiParams ) {
        Observable<HttpResult<String>> observable = Apis.getInstance( ).getRetrofit( )
                .create( SystemApis.class ).getBaseApiUrl( getApiParams.getBaseParamsMap( ) );

        ObservableHelper.getInstance( )
                .toSubscriber( observable, new ProgressSubscriber<String>( ) {
                    @Override
                    protected void _onError( String message ) {
                        LogUtil.d( message );
                    }

                    @Override
                    protected void _onNext( String str ) {
                        mView.setTextView( str );
                    }
                } );
    }
}
