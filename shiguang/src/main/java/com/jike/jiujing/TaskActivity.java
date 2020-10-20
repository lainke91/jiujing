package com.jike.jiujing;

import android.os.CpuUsageInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jike.jiujing.Adapter.TaskAdapter;
import com.jike.jiujing.base.BaseActivity;
import com.yalantis.library.Koloda;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TaskActivity extends BaseActivity {

    @BindView(R.id.koloda) Koloda koloda;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_task;
    }

    @Override
    public void initView() {
        super.initView();

        List<Integer> dataList = new ArrayList<>();
        dataList.add(0);
        dataList.add(0);
        dataList.add(0);
        dataList.add(0);
        dataList.add(0);
        dataList.add(0);
        dataList.add(0);
        dataList.add(0);
        dataList.add(0);
        dataList.add(0);
        koloda.setAdapter(new TaskAdapter(currentContext, dataList));
        koloda.setNeedCircleLoading(true);
    }
}
