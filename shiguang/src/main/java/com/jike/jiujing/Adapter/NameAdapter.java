package com.jike.jiujing.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jike.jiujing.R;

import java.util.List;

/**
 * Created by sjgb8d on 2020/9/15
 */
public class NameAdapter extends RecyclerView.Adapter<NameAdapter.ViewHolder> {

    private List<String> mNameList;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.name_iteam,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String name=mNameList.get(position);
        viewHolder.name.setText(name);

    }

    @Override
    public int getItemCount() {
        return mNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tv_name);
        }
    }

    public NameAdapter(List<String>nameList){
        mNameList=nameList;
    }
}
