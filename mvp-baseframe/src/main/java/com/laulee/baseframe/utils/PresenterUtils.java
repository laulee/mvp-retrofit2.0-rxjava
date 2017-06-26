package com.laulee.baseframe.utils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by laulee on 17/6/2.
 */

public class PresenterUtils {

    /**
     * 通过反射获取范型类
     *
     * @param obj
     * @param index
     * @param <T>
     * @return
     */
    public static <T> T getParadigm( Object obj, int index ) {
        try {
            return ( (Class<T>) ( (ParameterizedType) ( obj.getClass( ).getGenericSuperclass( ) ) )
                    .getActualTypeArguments( )[index] ).newInstance( );
        } catch( Exception e ) {
            e.printStackTrace( );
        }
        return null;
    }
}
