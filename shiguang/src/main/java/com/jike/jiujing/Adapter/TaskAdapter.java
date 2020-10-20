package com.jike.jiujing.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jike.jiujing.R;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> dataList = new ArrayList();


    public TaskAdapter(Context context, List<Integer> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Integer getItem(int position) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_koloda, parent, false);
            holder = new DataViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (DataViewHolder)convertView.getTag();
        }
        holder.bindData(context, getItem(position));
        return convertView;
    }



    class DataViewHolder extends RecyclerView.ViewHolder {

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bindData(Context context, Integer data) {
//            val transforms = RequestOptions().transforms(CenterCrop(), RoundedCorners(20))
//            Glide.with(context)
//                    .load(data)
//                    .apply(transforms)
//                    .into(picture)
        }

    }
}
