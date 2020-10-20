package com.jike.jiujing;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BackgroundActivity extends AppCompatActivity implements LoginFragmentInterface, BackgroundActivityInterface, TilesRendererInterface {

    @BindView(R.id.gl_surface_view)
    GLSurfaceView mGlSurfaceView;

    private LoginFragment mLoginFragment;
    private GroupFragment mGroupFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_background);
        ButterKnife.bind(this);
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
        mLoginFragment = LoginFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_downward, 0, R.anim.slide_downward, 0)
                .add(R.id.container, mLoginFragment, "login")
                .commit();
    }

    @Override
    public void onLoginClick() {
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

    @Override
    public void goToSide(int cx, int cy, boolean appBarExpanded, String side) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = GroupFragment.newInstance();
        ft.add(R.id.container, fragment, side).commit();

    }

    @Override
    public void removeAllFragmentExcept(String tag) {
        List<Fragment> frags = getSupportFragmentManager().getFragments();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment frag;
        for (int i = 0; i < frags.size(); i++) {
            frag = frags.get(i);
            if (frag == null) {
                continue;
            }
            if (tag == null || !tag.equals(frag.getTag())) {
                ft.remove(frag);
            }
        }
        ft.commit();

    }

    @Override
    public void onTilesFinished() {

    }
}
