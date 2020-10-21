package com.jike.jiujing.task;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;

import com.jike.jiujing.Adapter.TaskAdapter;
import com.jike.jiujing.R;
import com.jike.jiujing.base.BaseActivity;
import com.jike.jiujing.common.entry.Task;
import com.yalantis.library.Koloda;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TaskActivity extends BaseActivity {

    @BindView(R.id.koloda) Koloda koloda;
    private static final String PARAM_IS_CAPTAIN_TASK = "isCaptainTask";

    public static void go(Context context, boolean isCaptainTask) {
        Intent intent = new Intent(context, TaskActivity.class);
        intent.putExtra(PARAM_IS_CAPTAIN_TASK, isCaptainTask);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task;
    }

    @Override
    public void initView() {
        super.initView();

        List<Task> dataList = new ArrayList<>();
        boolean isCaptainTask = getIntent().getBooleanExtra(PARAM_IS_CAPTAIN_TASK, false);
        if(isCaptainTask) {
            String[] ids = getResources().getStringArray(R.array.captain_task_id);
            String[] titles = getResources().getStringArray(R.array.captain_task_title);
            TypedArray imgs = getResources().obtainTypedArray(R.array.captain_task_img);
            for(int i = 0; i < ids.length; i++){
                dataList.add(new Task(ids[i], titles[i], imgs.getDrawable(i)));
            }
        } else {
            String[] ids = getResources().getStringArray(R.array.team_task_id);
            String[] titles = getResources().getStringArray(R.array.team_task_title);
            TypedArray imgs = getResources().obtainTypedArray(R.array.team_task_img);
            for(int i = 0; i < ids.length; i++){
                dataList.add(new Task(ids[i], titles[i], imgs.getDrawable(i)));
            }
        }
        koloda.setAdapter(new TaskAdapter(currentContext, dataList));
        koloda.setNeedCircleLoading(true);
    }
}
