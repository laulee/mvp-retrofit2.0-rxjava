package com.laulee.retrofit2.app;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sean on 16/3/17.
 */
public class AppConfig {

    private static Set<Activity> allActivities;

    /**
     * 杀死activity
     *
     * @param activityClass
     */
    public static void killActivity( Activity activityClass ) {
        if( allActivities == null )
            return;
        for( Activity activity : allActivities ) {
            if( activity.equals( activityClass ) ) {
                allActivities.remove( activityClass );
                activity.finish( );
            }
        }
    }

    /**
     * 添加activity
     *
     * @param act
     */
    public static void addActivity( Activity act ) {
        if( allActivities == null ) {
            allActivities = new HashSet<Activity>( );
        }
        allActivities.add( act );
    }

    /**
     * 移除activity
     *
     * @param act
     */
    public static void removeActivity( Activity act ) {
        if( allActivities != null ) {
            allActivities.remove( act );
        }
    }

    /**
     * 退出app
     */
    public static void exitApp() {
        if( allActivities != null ) {
            synchronized(allActivities) {
                for( Activity act : allActivities ) {
                    act.finish( );
                }
            }
        }
        android.os.Process.killProcess( android.os.Process.myPid( ) );
        System.exit( 0 );
    }
}
