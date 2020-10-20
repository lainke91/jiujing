package com.jike.jiujing;

/**
 * Created by sjgb8d on 2020/9/14
 */
public interface BackgroundActivityInterface {
    void goToSide(int cx, int cy, boolean appBarExpanded, String side);

    void removeAllFragmentExcept(String tag);
}
