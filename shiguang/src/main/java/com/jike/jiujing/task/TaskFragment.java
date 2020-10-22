package com.jike.jiujing.task;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.jike.jiujing.App;
import com.jike.jiujing.R;
import com.jike.jiujing.base.BaseFragment;
import com.jike.jiujing.common.entry.CaptainUser;
import com.jike.jiujing.common.utils.ToastUtils;

import butterknife.OnClick;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by sjgb8d on 2020/10/12
 */
public class TaskFragment extends BaseFragment implements ScreenShotable {
    private View containerView;
    private Bitmap bitmap;

    public static TaskFragment newInstance() {
        TaskFragment taskFragment = new TaskFragment();
        return taskFragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_task;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container_task);
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
                TaskFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @OnClick(R.id.ly_captain)
    public void onCaptainClick() {
        TaskActivity.go(currentContext, true);
    }

    @OnClick(R.id.ly_team)
    public void onTeamClick() {
        String lock = App.getInstance().getUser().getTeamLock();
        String teamId = App.getInstance().getUser().getTeamID();
        if("0".equals(teamId) || "1".equals(lock)) {
            TaskActivity.go(currentContext, false);
        } else {
            ToastUtils.show("请先完成队长任务");
        }
    }
}
