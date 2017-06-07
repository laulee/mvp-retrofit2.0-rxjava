package com.laulee.baseframe.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;

/**
 * Created by laulee on 17/3/1.
 */

public class SystemUtil {

    private static String deviceId;

    /**
     * 检测网络连接
     *
     * @return
     */
    public static boolean isNetworkConnected( Context context ) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getApplicationContext( ).getSystemService( Context.CONNECTIVITY_SERVICE );
        return connectivityManager.getActiveNetworkInfo( ) != null;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px( Context context, float dpValue ) {
        final float scale = context.getResources( ).getDisplayMetrics( ).density;
        return (int) ( dpValue * scale + 0.5f );
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp( Context context, float pxValue ) {
        final float scale = context.getResources( ).getDisplayMetrics( ).density;
        return (int) ( pxValue / scale + 0.5f );
    }

    /**
     * 获取设备唯一ID
     *
     * @param context
     * @return
     */
    public static String getDeviceOnlyId( Context context ) {
        if( deviceId != null ) {
            return deviceId;
        } else {
            TelephonyManager TelephonyMgr = (TelephonyManager) context
                    .getSystemService( Context.TELEPHONY_SERVICE );
            deviceId = TelephonyMgr.getDeviceId( );
            return deviceId;
        }
    }


    /**
     * 获取app版本
     *
     * @param context
     * @return
     */
    public static String getAPPVersion( Context context ) {
        String version = "";
        try {
            PackageManager manager = context.getPackageManager( );
            PackageInfo info = manager.getPackageInfo( context.getPackageName( ), 0 );
            version = info.versionName;
            return version;
        } catch( Exception e ) {
            e.printStackTrace( );
            return version;
        }
    }
}
