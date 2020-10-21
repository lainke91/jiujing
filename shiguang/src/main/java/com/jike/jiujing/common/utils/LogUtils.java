package com.jike.jiujing.common.utils;

import android.util.Log;

public class LogUtils {
    public static void show(String TAG,String str) {
        str = str.trim();
        int index = 0;
        int maxLength = 3000;
        String sub;
        while (index < str.length()) {
            // java的字符不允许指定超过总的长度end
            if (str.length() <= index + maxLength) {
                sub = str.substring(index);
            } else {
                sub = str.substring(index, index + maxLength);
            }
            index += maxLength;
            Log.i(TAG, sub.trim());
        }
    }
}
