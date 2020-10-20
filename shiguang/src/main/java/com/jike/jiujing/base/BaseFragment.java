package com.jike.jiujing.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    protected View view;
    protected Context currentContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        currentContext = getActivity();
        initView();
        return view;
    }

    protected int getLayoutId() {
        return 0;
    }

    protected void initView() {

    }

    protected void startActivity(Class activity){
        startActivity(new Intent(currentContext, activity));
    }

    protected void startActivityForResult(Class activity, int requestCode){
        startActivityForResult(new Intent(currentContext, activity), requestCode);
    }
}
