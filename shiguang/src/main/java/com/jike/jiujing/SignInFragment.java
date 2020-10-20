package com.jike.jiujing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jike.jiujing.base.BaseFragment;
import com.jike.jiujing.signin.SigninActivity;
import com.jike.jiujing.signin.SigninAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by sjgb8d on 2020/10/13
 */
public class SignInFragment extends BaseFragment implements ScreenShotable, SigninAdapter.ListListener {
    private View containerView;
    private Bitmap bitmap;


    private List<String> dataList = new ArrayList<>();
    @BindView(R.id.recycleView) RecyclerView recycleView;
    private SigninAdapter adapter;

    private ActionMode mActionMode;

    public static SignInFragment newInstance() {
        SignInFragment signInFragment= new SignInFragment();
        return signInFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_signin;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container_signin);
    }

    @Override
    protected void initView() {
        super.initView();
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(currentContext));
        recycleView.setItemAnimator(new DefaultItemAnimator());

        for(int i = 0; i < 10; i++) {
            dataList.add("");
        }
        adapter = new SigninAdapter(currentContext, dataList);
        adapter.setListListener(this);
        recycleView.setAdapter(adapter);
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
                SignInFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public void onListItemClick(int position) {
//        toggleSelection(position);
        Intent intent = new Intent(currentContext, SigninActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public boolean onListItemLongClick(int position) {
        return false;
    }


    private void toggleSelection(final int position) {
        adapter.toggleSelection(position);
//        int count = adapter.getSelectedItemCount();
//        if (count == 0) {
//            mActionMode.finish();
//        } else {
//            mActionMode.setTitle(String.valueOf(count));
//            mActionMode.invalidate();
//        }
    }


}

