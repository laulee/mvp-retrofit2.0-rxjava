package com.laulee.retrofit2.utils;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具
 * Created by sean on 15/9/23.
 */
public class EncodeUtil
{
    /**
     * base64 编码
     *
     * @param value
     * @return
     */
    public static String Base64( String value )
    {
        return Base64.encodeToString( value.getBytes( ), Base64.DEFAULT );
    }


    /**
     * 加密
     *
     * @param value
     * @return
     */
    public static String encode( String value )
    {
        String str = Base64( value );

        if( str.length( ) < 21 )
        {
            return value;
        }

        StringBuffer sb = new StringBuffer( );

        sb.append( str.substring( 10, 20 ) );
        sb.append( str.substring( 0, 10 ) );
        sb.append( str.substring( 20, str.length( ) ) );

        return Base64( sb.toString( ) );
    }

    /**
     * base64后MD5加密
     *
     * @param encodeStr
     * @return
     */
    public static String getParamsMD5( String encodeStr )
    {
        return getMD5( Base64( encodeStr ) );
    }

    /**
     * MD5加密
     *
     * @param encodeStr
     * @return
     */
    public static String getMD5( String encodeStr )
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance( "MD5" );
            md.update( encodeStr.getBytes( ) );
            byte b[] = md.digest( );

            int i;

            StringBuffer buf = new StringBuffer( "" );
            for( byte aB : b )
            {
                i = aB;
                if( i < 0 )
                    i += 256;
                if( i < 16 )
                    buf.append( "0" );
                buf.append( Integer.toHexString( i ) );
            }
            return buf.toString( );//32位的加密
            //System.out.println("result: " + buf.toString().substring(8,24));//16位的加密
        } catch( NoSuchAlgorithmException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace( );
        }
        return encodeStr;
    }

}
