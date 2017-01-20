package com.laulee.retrofit2.base;

import android.content.Context;
import android.text.TextUtils;

import com.laulee.retrofit2.utils.EncodeUtil;
import com.laulee.retrofit2.utils.LogUtil;
import com.laulee.retrofit2.utils.SystemUtil;
import com.laulee.retrofit2.utils.TimeUtil;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BaseParams {
    private final String GET_METHOD = "get";
    private final String STRING = "java.lang.String";
    private final String INT = "int";
    private final String source = "201";
    private final String TIME = "time";
    private final String LANG = "lang";
    private final String lang = "zh_CN";
    private final String PARAMS = "params";
    private final String SIGN = "sign";
    private final String SOURCE = "source";

    private final String VERSION = "version";
    private final String DEVICE_UDID = "device-udid";

    private String version;
    private String deviceOnlyId;

    public BaseParams( Context context ) {
        deviceOnlyId = SystemUtil.getDeviceOnlyId( context );
        version = SystemUtil.getAPPVersion( context );
    }

    /**
     * 获取请求参数
     *
     * @return
     */
    public String getParams() {
        String params = "";
        JSONObject json = new JSONObject( );
        for( Field field : this.getClass( ).getFields( ) ) {
            try {
                if( field.getType( ).getName( ).equals( INT ) ) {
                    int intParams = (Integer) field.get( this );
                    json.put( field.getName( ), intParams );
                } else if( field.getType( ).getName( ).equals( STRING ) ) {
                    String str = (String) field.get( this );
                    if( !TextUtils.isEmpty( str ) )
                        json.put( field.getName( ), str );
                }
            } catch( Exception e ) {
                LogUtil.e( e.getClass( ).getName( ), e.toString( ) );
            }
        }

        LogUtil.json( json.toString( ) );
        params = EncodeUtil.encode( json.toString( ) );
        return params;
    }

    /**
     * 获得参数Map对象
     *
     * @return
     */
    public Map<String, String> getBaseParamsMap() {
        StringBuffer sb = new StringBuffer( );
        sb.append( getParams( ) );
        sb.append( TimeUtil.getYMDTime( ) );

        StringBuffer encodeStr = new StringBuffer( );
        encodeStr.append( EncodeUtil.getMD5( sb.toString( ) ) );

        Map<String, String> basicParams = new HashMap<>( );
        basicParams.put( SOURCE, source );
        basicParams.put( TIME, TimeUtil.getYMDTime( ) );
        basicParams.put( LANG, lang );
        basicParams.put( PARAMS, getParams( ) );
        basicParams.put( SIGN, EncodeUtil.getMD5( encodeStr.toString( ) ) );
        basicParams.put( VERSION, version );
        basicParams.put( DEVICE_UDID, deviceOnlyId );
        return basicParams;
    }

}
