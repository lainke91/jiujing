package com.jike.jiujing.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.jike.jiujing.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context currentContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentContext = this;
        beforeAddContent();
        initRequestedOrientation();
        if(isTranslucentStatus()) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  //透明导航栏
        }
        if(getLayoutId() > 0) {
            setContentView(getLayoutId());
            ButterKnife.bind(this);
        }
        initView();
    }

    public void initView() {

    }

    protected void beforeAddContent() {
    }

    protected void initRequestedOrientation(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
    }

    protected int getLayoutId() {
        return -1;
    }

    protected boolean isTranslucentStatus(){
        return false;
    }

    protected int getCompatColor(int resId){
        return ContextCompat.getColor(this, resId);
    }

    protected void hideBottomMenu() {
        //隐藏虚拟按键，并且全屏
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    protected void startActivity(Class activity){
        startActivity(new Intent(currentContext, activity));
    }

    protected void startActivityForResult(Class activity, int requestCode){
        startActivityForResult(new Intent(currentContext, activity), requestCode);
    }
}
