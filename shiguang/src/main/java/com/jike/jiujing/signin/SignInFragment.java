package com.jike.jiujing.signin;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jike.jiujing.R;
import com.jike.jiujing.base.BaseFragment;
import com.jike.jiujing.folding.FoldingCell;
import com.jike.jiujing.folding.Item;

import java.util.ArrayList;

import butterknife.BindView;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by sjgb8d on 2020/10/13
 */
public class SignInFragment extends BaseFragment implements ScreenShotable {
    private View containerView;
    private Bitmap bitmap;

    @BindView(R.id.listView) ListView listView;

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

        final ArrayList<Item> items = Item.getTestingList();

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final SigninAdapter adapter = new SigninAdapter(currentContext, items);
        listView.setAdapter(adapter);
        // set on click event listener to list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos);
            }
        });


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

}

