package com.jike.jiujing.common.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

public class BaseDialog extends Dialog {

    public BaseDialog(@NonNull Context context) {
        super(context);
        initView();
    }

    protected void initView() {
        if(getLayoutId() > 0) {
            setContentView(getLayoutId());
            ButterKnife.bind(this);
        }
    }

    protected int getLayoutId() {
        return -1;
    }

    protected void setWidthPercent(float percent) {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        lp.width = (int)(dm.widthPixels * percent);
        dialogWindow.setAttributes(lp);
    }
}

