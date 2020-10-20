package com.jike.jiujing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yalantis.starwars.TilesFrameLayout;
import com.yalantis.starwars.interfaces.TilesFrameLayoutListener;

public class MainActivity extends AppCompatActivity implements TilesFrameLayoutListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TilesFrameLayout mTilesFrameLayout = (TilesFrameLayout) findViewById(R.id.tiles_frame_layout);
        mTilesFrameLayout.setOnAnimationFinishedListener(this);

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTilesFrameLayout.startAnimation();
                    }
                });
            }
        }.start();


    }


    @Override
    public void onAnimationFinished() {

    }
}
