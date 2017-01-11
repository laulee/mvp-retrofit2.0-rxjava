package com.laulee.retrofit2.model;

import com.laulee.retrofit2.bean.params.GetApiParams;

/**
 * Created by laulee on 16/12/27.
 */

public interface GetApiSource {

    String getApiUrl( GetApiParams getApiParams );
}
