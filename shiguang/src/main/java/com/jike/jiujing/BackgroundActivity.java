package com.jike.jiujing;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jike.jiujing.common.entry.CaptainUser;
import com.jike.jiujing.common.event.ExitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BackgroundActivity extends AppCompatActivity implements LoginFragmentInterface, BackgroundActivityInterface, TilesRendererInterface {

    @BindView(R.id.gl_surface_view)
    GLSurfaceView mGlSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);
        ButterKnife.bind(this);
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        // Check if the system supports OpenGL ES 2.0.
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

        if (supportsEs2) {
            // Request an OpenGL ES 2.0 compatible context.
            mGlSurfaceView.setEGLContextClientVersion(2);

            // Set the renderer to our demo renderer, defined below.
            ParticleSystemRenderer mRenderer = new ParticleSystemRenderer(mGlSurfaceView);
            mGlSurfaceView.setRenderer(mRenderer);
            mGlSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        } else {
            throw new UnsupportedOperationException();
        }

        if (savedInstanceState == null) {
            showLogin();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGlSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGlSurfaceView.onResume();
    }

    private void showLogin() {
        CaptainUser captainUser = App.getInstance().getUser();
        if(captainUser == null) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_downward, 0, R.anim.slide_downward, 0)
                    .replace(R.id.container, LoginFragment.newInstance(), "login")
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_upward, 0)
                    .replace(R.id.container, GroupFragment.newInstance(), "login")
                    .commit();
        }
    }

    @Override
    public void onLoginClick() {
        if("0".equals(App.getInstance().getUser().getTeamID())) {
            startActivity(new Intent(this, ContentActivity.class));
        } else {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_upward, 0)
                            .replace(R.id.container, GroupFragment.newInstance(), "group")
                            .commit();
                }
            });
        }
    }

    @Override
    public void goToSide(int cx, int cy, boolean appBarExpanded, String side) {
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        Fragment fragment = GroupFragment.newInstance();
//        ft.add(R.id.container, fragment, side).commit();
    }

    @Override
    public void removeAllFragmentExcept(String tag) {
//        List<Fragment> frags = getSupportFragmentManager().getFragments();
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        Fragment frag;
//        for (int i = 0; i < frags.size(); i++) {
//            frag = frags.get(i);
//            if (frag == null) {
//                continue;
//            }
//            if (tag == null || !tag.equals(frag.getTag())) {
//                ft.remove(frag);
//            }
//        }
//        ft.commit();
    }

    @Override
    public void onTilesFinished() {
    }

    @Subscribe
    public void onExitEvent(ExitEvent event) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, LoginFragment.newInstance(), "login")
                        .commitAllowingStateLoss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
