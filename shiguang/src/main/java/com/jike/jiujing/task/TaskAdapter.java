package com.jike.jiujing.task;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jike.jiujing.R;
import com.jike.jiujing.common.adapter.CommonAdapter;
import com.jike.jiujing.common.adapter.ViewHolder;
import com.jike.jiujing.common.entry.Task;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends CommonAdapter<Task> {

    private String teamId;

    public interface OnSubmitClickListener {
        void onSubmit(int pos);
    }
    private OnSubmitClickListener onSubmitClickListener;

    public void setOnSubmitClickListener(OnSubmitClickListener onSubmitClickListener) {
        this.onSubmitClickListener = onSubmitClickListener;
    }

    public TaskAdapter(Context context, List<Task> datas, String teamId) {
        super(context, datas, R.layout.item_task);
        this.teamId = teamId;
    }

    @Override
    public void convert(ViewHolder holder, final Task data, final int pos) {
        holder.setText(R.id.tv_task_id, data.getTaskId())
                .setText(R.id.tv_task_title, data.getTaskTitle())
                .setImageDrawable(R.id.iv_task_img, data.getTaskImg());


        final Button btnSubmit = holder.getView(R.id.btn_submit);
        if("0".equals(teamId)) {
            btnSubmit.setVisibility(View.VISIBLE);
            btnSubmit.setText("任务码");
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnSubmit.setText(String.format("任务码：%s", data.getPassword()));
                }
            });

            holder.setShow(R.id.iv_status, false);
        } else {
            btnSubmit.setText("去完成");
            if ("0".equals(data.getTaskState())) {
                holder.setShow(R.id.iv_status, true)
                        .setImageResource(R.id.iv_status, R.mipmap.status_incomplete);
                btnSubmit.setVisibility(View.VISIBLE);
                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onSubmitClickListener != null) {
                            onSubmitClickListener.onSubmit(pos);
                        }
                    }
                });
            } else {
                holder.setImageResource(R.id.iv_status, R.mipmap.status_completed);
                btnSubmit.setVisibility(View.GONE);
            }
        }

    }

}
