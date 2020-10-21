package com.jike.jiujing.common.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jike.jiujing.App;
import com.jike.jiujing.R;

public class ToastUtils {

    public static void show(int resId) {
        show(App.getAppContext().getString(resId));
    }

    public static void show(String msg) {
        Toast toast = new Toast(App.getAppContext());
        View view = View.inflate(App.getAppContext(), R.layout.common_toast, null);
        TextView textView = view.findViewById(R.id.tv_msg);
        textView.setText(msg);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
