package com.jike.jiujing.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jike.jiujing.R;
import com.jike.jiujing.common.entry.Task;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends BaseAdapter {
    private Context context;
    private List<Task> dataList = new ArrayList();


    public TaskAdapter(Context context, List<Task> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Task getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
            holder = new DataViewHolder(convertView);
            holder.tvTaskId = convertView.findViewById(R.id.tv_task_id);
            holder.tvTaskTitle = convertView.findViewById(R.id.tv_task_title);
            holder.ivTaskImg = convertView.findViewById(R.id.iv_task_img);
            convertView.setTag(holder);
        } else {
            holder = (DataViewHolder)convertView.getTag();
        }
        holder.bindData(context, getItem(position));
        return convertView;
    }



    class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTaskId;
        private TextView tvTaskTitle;
        private ImageView ivTaskImg;


        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindData(Context context, Task data) {
            tvTaskId.setText(data.getTaskId());
            tvTaskTitle.setText(data.getTaskTitle());
            ivTaskImg.setImageDrawable(data.getTaskImg());
        }

    }
}
