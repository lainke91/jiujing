package com.jike.jiujing.common.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class KeyboardUtils {
    private KeyboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isShowSoftInput(Context context, EditText et) {  //判断键盘是否显示
        return ((InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE)).isActive(et);
    }

    public static boolean isShowSoftInput(Context context) {  //判断键盘是否显示
        return ((InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE)).isActive();
    }


    public static void toggleSoftInput(Context context) {  //切换键盘显示与否状态
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public static void toggleSoftInput(Context context, EditText et) {  //切换键盘显示与否状态
        if(isShowSoftInput(context, et)){
            hideSoftInput(context, et);
        } else {
            showSoftInput(context, et);
        }
    }

    public static void showSoftInput(Context context, EditText et) {  //显示软键盘
        et.setFocusable(true);
        et.setFocusableInTouchMode(true);
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, 0);
    }

    public static void hideSoftInput(Context context, View et) {  //隐藏软键盘
        et.clearFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

    protected void hideInputKeyboard(Context context,View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**弹起键盘 */
    public static void showInputKeyboard(Context context,View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, 0);
    }
}
