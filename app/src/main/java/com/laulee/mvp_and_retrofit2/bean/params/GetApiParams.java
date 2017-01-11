package com.laulee.mvp_and_retrofit2.bean.params;

import android.content.Context;

import com.laulee.mvp_and_retrofit2.base.BaseParams;

/**
 * Created by laulee on 16/12/25.
 */

public class GetApiParams extends BaseParams {
    public String method = "api_base_url";

    public GetApiParams( Context context ) {
        super( context );
    }
}
