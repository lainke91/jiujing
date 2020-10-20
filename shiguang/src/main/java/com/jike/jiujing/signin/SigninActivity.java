package com.jike.jiujing.signin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.jike.jiujing.R;
import com.jike.jiujing.base.BaseActivity;

public class SigninActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_signin;
    }


    @Override
    public void initView() {
        super.initView();
        replaceFragment(new SignInDetailsFragment(), false, R.id.wrapper_fragment);
    }

    protected void replaceFragment(final Fragment fragment, final boolean addToBackStack,
                                   final int containerId) {
        String backStateName = fragment.getClass().getName();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            transaction.addToBackStack(backStateName);
        }
        transaction.commit();
        invalidateOptionsMenu();
    }

}
