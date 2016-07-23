package com.example.test.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/7/21.
 */
public class PreferencesUtils {
    private static SharedPreferences mPreferences;

    //初始化sharepreference
    private static SharedPreferences getSp(Context context) {
        if (mPreferences == null) {
            mPreferences = context.getSharedPreferences("oa", Context.MODE_PRIVATE);
        }
        return mPreferences;
    }


    /**
     * 获得boolean类型数据没有时候默认为false
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * 获得boolean类型的数据
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = getSp(context);
        return sp.getBoolean(key, defValue);
    }

    /**
     * 设置boolean类型数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = getSp(context);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    /**
     * 获得String类型数据 默认null
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * 获得String类型数据
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = getSp(context);
        return sp.getString(key, defValue);
    }

    /**
     * 设置String类型数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = getSp(context);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }
}
