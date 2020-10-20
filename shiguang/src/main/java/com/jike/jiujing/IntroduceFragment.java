package com.jike.jiujing;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.jike.jiujing.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by sjgb8d on 2020/10/13
 */
public class IntroduceFragment extends BaseFragment implements ScreenShotable {
    private View containerView;
    private Bitmap bitmap;

    @BindView(R.id.tv_title1) TextView tvTitle1;
    @BindView(R.id.tv_title2) TextView tvTitle2;
    @BindView(R.id.tv_title3) TextView tvTitle3;
    @BindView(R.id.tv_title4) TextView tvTitle4;

    public static IntroduceFragment newInstance() {
        IntroduceFragment introduceFragment = new IntroduceFragment();
        return introduceFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_introduce;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container_introduce);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                IntroduceFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @OnClick({R.id.ly_item1, R.id.ly_item2, R.id.ly_item3, R.id.ly_item4})
    public void onItemClick(View v) {
        String title = "";
        switch (v.getId()){
            case R.id.ly_item1:
                title = tvTitle1.getText().toString();
                break;
            case R.id.ly_item2:
                title = tvTitle2.getText().toString();
                break;
            case R.id.ly_item3:
                title = tvTitle3.getText().toString();
                break;
            case R.id.ly_item4:
                title = tvTitle4.getText().toString();
                break;
        }
        GameListActivity.go(currentContext, title);
    }
}
