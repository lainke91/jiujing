package com.jike.jiujing.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by VMac on 17/1/4.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context context;
    protected List<T> datas;
    private int layoutId;

    public CommonAdapter(Context context, List<T> datas, int layoutId){
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        ViewHolder holder = ViewHolder.get(context, view, viewGroup, layoutId, i);
        convert(holder, getItem(i), i);
        return holder.getConvertView();
    };

    public abstract void convert(ViewHolder holder,T t,int pos);

}
