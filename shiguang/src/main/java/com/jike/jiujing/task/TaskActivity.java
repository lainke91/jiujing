package com.jike.jiujing.task;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.view.View;

import com.jike.jiujing.App;
import com.jike.jiujing.ContentActivity;
import com.jike.jiujing.R;
import com.jike.jiujing.base.BaseActivity;
import com.jike.jiujing.common.entry.CaptainUser;
import com.jike.jiujing.common.entry.ResultData;
import com.jike.jiujing.common.entry.Task;
import com.jike.jiujing.common.event.EnergyEvent;
import com.jike.jiujing.common.event.ExitEvent;
import com.jike.jiujing.common.param.TaskParam;
import com.jike.jiujing.common.service.ApiLoader;
import com.jike.jiujing.common.service.Callback;
import com.jike.jiujing.common.utils.SPUtils;
import com.jike.jiujing.common.view.PasswordDialog;
import com.jike.jiujing.common.view.PasswordEditView;
import com.yalantis.library.Koloda;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TaskActivity extends BaseActivity implements TaskAdapter.OnSubmitClickListener {

    private static final String PARAM_IS_CAPTAIN_TASK = "isCaptainTask";
    @BindView(R.id.koloda) Koloda koloda;
    private TaskAdapter adapter;
    private List<Task> dataList = new ArrayList<>();

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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void initView() {
        super.initView();

        CaptainUser user = App.getInstance().getUser();
        List<Task> taskList = user.getTask();

        boolean isCaptainTask = getIntent().getBooleanExtra(PARAM_IS_CAPTAIN_TASK, false);

        String[] ids;
        String[] titles;
        String[] codes;
        TypedArray imgs;
        if (isCaptainTask) {
            ids = getResources().getStringArray(R.array.captain_task_id);
            titles = getResources().getStringArray(R.array.captain_task_title);
            codes = getResources().getStringArray(R.array.captain_task_code);
            imgs = getResources().obtainTypedArray(R.array.captain_task_img);
        } else {
            ids = getResources().getStringArray(R.array.team_task_id);
            titles = getResources().getStringArray(R.array.team_task_title);
            codes = getResources().getStringArray(R.array.team_task_code);
            imgs = getResources().obtainTypedArray(R.array.team_task_img);
        }
        boolean hasTask = taskList != null && taskList.size() != 0;
        String teamId = App.getInstance().getUser().getTeamID();
        for (int i = 0; i < ids.length; i++) {
            if (!"0".equals(teamId)) {
                if (hasTask) {
                    for (Task item : taskList) {
                        if (item.getTaskId().equals(ids[i])) {
                            dataList.add(new Task(ids[i], titles[i], codes[i], imgs.getDrawable(i), item.getTaskState()));
                            break;
                        }
                    }
                }
            } else {
                dataList.add(new Task(ids[i], titles[i], codes[i], imgs.getDrawable(i), "0"));
            }
        }
        if(dataList.size() == 0){
            koloda.setVisibility(View.GONE);
        } else {
            adapter = new TaskAdapter(currentContext, dataList, user.getTeamID());
            adapter.setOnSubmitClickListener(this);
            koloda.setAdapter(adapter);
            koloda.setNeedCircleLoading(true);
        }
    }

    @Override
    public void onSubmit(final int pos) {
        final PasswordDialog dialog = new PasswordDialog(currentContext);
        dialog.setOnPasswordCompleteListener(new PasswordEditView.OnPasswordCompleteListener() {
            @Override
            public void onComplete(String password) {
                TaskParam param = new TaskParam();
                final CaptainUser user = App.getInstance().getUser();
                param.setTeamID(user.getTeamID());
                param.setTaskID(dataList.get(pos).getTaskId());
                param.setTaskPassword(password);
                new ApiLoader().taskFinish(param).subscribe(new Callback<ResultData<CaptainUser>>(){
                    @Override
                    public void onSuccess (ResultData<CaptainUser> result) {
                        if(result.isSuccess()) {
                            dialog.dismiss();
                            if(user.getTask() != null) {
                                for (Task item : user.getTask()) {
                                    if (item.getTaskId().equals(dataList.get(pos).getTaskId())){
                                        item.setTaskState("1");
                                    }
                                }
                            }
                            dataList.get(pos).setTaskState("1");
                            user.setTeamEnergy(result.getData().getTeamEnergy());
                            user.setTeamLock(result.getData().getTeamLock());

                            App.getInstance().setUser(user);
                            adapter = new TaskAdapter(currentContext, dataList, user.getTeamID());
                            adapter.setOnSubmitClickListener(TaskActivity.this);
                            koloda.setAdapter(adapter);
                            EventBus.getDefault().post(new EnergyEvent());
                        }
                    }
                });
            }
        });
        dialog.show();
    }
}
