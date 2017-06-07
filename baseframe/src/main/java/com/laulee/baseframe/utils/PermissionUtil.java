package com.laulee.baseframe.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by laulee on 17/6/5.
 */

public class PermissionUtil {

    /**
     * 检查是否含有某个权限
     *
     * @param permissionName
     * @param context
     * @return
     */
    public static boolean checkPermission( String permissionName, Context context ) {
        PackageManager packageManager = context.getPackageManager( );
        boolean isAdded = false;
        isAdded = ( PackageManager.PERMISSION_GRANTED == packageManager
                .checkPermission( permissionName, context.getPackageName( ) ) );
        return isAdded;
    }

}
