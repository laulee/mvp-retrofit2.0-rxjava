package com.laulee.retrofit2.model.impl;

import com.laulee.retrofit2.bean.params.GetApiParams;
import com.laulee.retrofit2.model.GetApiSource;

/**
 * Created by laulee on 16/12/27.
 */

public class GetApiSourceImpl implements GetApiSource {

    @Override
    public String getApiUrl( GetApiParams getApiParams ) {

        final String[] apiUrl = new String[1];

//        Observable<HttpResult<String>> observable = Apis.getInstance( ).getRetrofit( )
//                .create( SystemApis.class ).getBaseApiUrl( getApiParams.getBaseParamsMap( ) );
//
//        ObservableHelper.getInstance( )
//                .toSubscriber( observable, new ProgressSubscriber<String>( ) {
//                    @Override
//                    protected void _onError( String message ) {
//                        LogUtil.d( message );
//                    }
//
//                    @Override
//                    protected void _onNext( String str ) {
//                        apiUrl[0] = str;
//                    }
//                } );
        return apiUrl[0];
    }
}
