package com.laulee.retrofit2.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by laulee on 15/10/18.
 */
public class TimeUtil {
    //24*60*60*1000 一天
    private static final long DAY_TIME = 24 * 60 * 60 * 1000;

    //60*60*1000 一小时
    private static final long HOUR_TIME = 60 * 60 * 1000;

    //60*1000 一分钟
    private static final long MIN_TIME = 60 * 1000;


    public static String getYMDTime() {
        SimpleDateFormat dataFormat = new SimpleDateFormat( "yyyyMMddHHmmss" );
        String nowTime = dataFormat.format( new Date( ) );
        return nowTime;
    }

    public static String getYMD( String time ) {
        time = time.replace( " ", "/" );
        String[] appTime = time.split( "/" );
        if( appTime.length > 0 ) {
            return appTime[0];
        }
        return "";
    }

    /**
     * 获取剩余时间
     *
     * @param limitTime
     * @return
     */
    public static String getSurplusTime( String limitTime ) {
        try {
            StringBuffer sb = new StringBuffer( );

            SimpleDateFormat limitFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            long time = limitFormat.parse( limitTime ).getTime( );
            SimpleDateFormat nowFormat = new SimpleDateFormat( "yyyyMMddHHmmss" );
            long nowTime = nowFormat.parse( getYMDTime( ) ).getTime( );

            long surplusTime = time - nowTime;
            int day = (int) ( surplusTime / ( DAY_TIME ) );
            int hour = (int) ( surplusTime % ( DAY_TIME ) / ( HOUR_TIME ) );
            int min = (int) ( surplusTime % ( DAY_TIME ) % ( HOUR_TIME ) / MIN_TIME );
            if( day > 0 && hour > -1 && hour != 0 ) {
                day += 1;
                sb.append( day );
                sb.append( "天" );
                return sb.toString( );
            }
            if( day > 0 && hour < 1 ) {
                sb.append( day );
                sb.append( "天" );
                return sb.toString( );
            }
            if( day < 1 && hour > 0 ) {
                sb.append( hour );
                sb.append( "小时" );
                return sb.toString( );
            }
            if( day < 1 && hour < 1 && min > 0 ) {
                sb.append( min );
                sb.append( "分钟" );
                return sb.toString( );
            }

        } catch( ParseException e ) {
            Log.e( "TimeUtil", "时间格式出错" );
            e.printStackTrace( );
            return "";
        }
        return "";
    }
}

