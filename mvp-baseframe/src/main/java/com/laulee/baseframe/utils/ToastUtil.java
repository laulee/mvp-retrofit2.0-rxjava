package com.laulee.baseframe.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by laulee on 16/4/11.
 */
public class ToastUtil {
    public static Toast appToast;

    public static Toast getInstance( Context context ) {
        if( appToast == null )
            appToast = Toast.makeText( context, "", Toast.LENGTH_LONG );

        appToast.setDuration( Toast.LENGTH_LONG );

        return appToast;
    }

    public static void showToast( String text ) {
        if( appToast != null ) {
            appToast.setText( text );
            appToast.show( );
        }
    }

    public static void cancel() {
        if( appToast != null )
            appToast.cancel( );
    }
}
