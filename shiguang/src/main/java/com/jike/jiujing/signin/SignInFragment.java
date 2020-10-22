package com.jike.jiujing.signin;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jike.jiujing.App;
import com.jike.jiujing.R;
import com.jike.jiujing.base.BaseFragment;
import com.jike.jiujing.common.entry.ResultData;
import com.jike.jiujing.common.entry.SignIn;
import com.jike.jiujing.common.entry.SignInActivity;
import com.jike.jiujing.common.entry.SignInActivityList;
import com.jike.jiujing.common.service.ApiLoader;
import com.jike.jiujing.common.service.Callback;
import com.jike.jiujing.folding.FoldingCell;
import com.jike.jiujing.folding.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by sjgb8d on 2020/10/13
 */
public class SignInFragment extends BaseFragment implements ScreenShotable {

    private List<SignInActivity> dataList = new ArrayList<>();

    private View containerView;
    private Bitmap bitmap;

    @BindView(R.id.listView) ListView listView;

    private SignInActivityList data;

    public static SignInFragment newInstance() {
        SignInFragment signInFragment = new SignInFragment();
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
        new ApiLoader().getActivity(App.getInstance().getUser().getTeamID()).subscribe(new Callback<ResultData<SignInActivityList>>(){
            @Override
            public void onSuccess (ResultData<SignInActivityList> result) {
                if(result.isSuccess()) {
                    data = result.getData();
                    initList();
                }
            }
        });






    }

    private void initList() {

        String[] ids = getResources().getStringArray(R.array.activity_id);
        String[] names = getResources().getStringArray(R.array.array_names);
        TypedArray imgs = getResources().obtainTypedArray(R.array.activity_img);
        String[] shorts = getResources().getStringArray(R.array.array_shorts);
        String[] longs = getResources().getStringArray(R.array.array_longs);
        String[] times = getResources().getStringArray(R.array.activity_time);
        if (data.getActivitylist() != null) {

            for (SignInActivity item : data.getActivitylist()) {
                for (int i = 0; i < names.length; i++) {
                    if(item.getActivityID().equals(ids[i])) {
                        SignInActivity data = new SignInActivity();
                        data.setActivityID(ids[i]);
                        data.setIcon(imgs.getDrawable(i));
                        data.setActivityName(names[i]);
                        data.setActivityStatus(item.getActivityStatus());
                        data.setActivityInfo(shorts[i]);
                        data.setActivityDesc(longs[i]);
                        data.setTime(times[i]);
                        data.setMemberlist(item.getMemberlist());
                        dataList.add(data);
                    }
                }
            }
        }

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        final SigninAdapter adapter = new SigninAdapter(currentContext, dataList);
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

    public void showMember(){
//        MemberPopup memberPopup = new MemberPopup(currentContext, )
//                .setOnSubmitListener(new MemberPopup.OnSubmitListener() {
//                    @Override
//                    public void onSubmit(int pos) {
//
//                    }
//                });
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

}

