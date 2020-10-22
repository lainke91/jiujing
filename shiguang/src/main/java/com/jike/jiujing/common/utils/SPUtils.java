package com.jike.jiujing.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

public class SPUtils {
    private static String SP_NAME = "shiguang";

    public static final String SP_LOGIN_DATA = "loginData";



    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    private static void initGet(Context context){
        if(sp == null){
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
    }

    private static void initSet(Context context){
        if (sp == null || editor == null) {
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
            editor = sp.edit();
        }
    }

    public static void setStringValue(Context context, String key, String value){
        initSet(context);
        editor.putString(key, value).commit();
    }

    public static String getStringValue(Context context, String key){
        initGet(context);
        return sp.getString(key, "");
    }

    public static void setBooleanValue(Context context, String key, boolean value){
        initSet(context);
        editor.putBoolean(key, value).commit();
    }

    public static boolean getBooleanValue(Context context, String key){
        initGet(context);
        return sp.getBoolean(key, false);
    }

    public static void setIntValue(Context context, String key, int value){
        initSet(context);
        editor.putInt(key, value).commit();
    }

    public static Integer getIntValue(Context context, String key){
        initGet(context);
        return sp.getInt(key, 0);
    }

    public static void setObjectValue(Context context, String key, Object value){
        initSet(context);
        String content = new Gson().toJson(value);
        editor.putString(key, content).commit();
    }

    public static Object getObjectValue(Context context, String key, Class cls){
        initGet(context);
        String json = sp.getString(key, "");
        if(TextUtils.isEmpty(json)){
            return null;
        }
        return new Gson().fromJson(json, cls);
    }


}
