package com.jike.jiujing.common.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by VMac on 17/1/4.
 */
public class ViewHolder {
    private final SparseArray<View> mViews;  //SparseArray是Android提供的一个key为Integer的Map,它的效率要比Map高
    private View mConvertView;
    private Context context;

    private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<View>();
        this.context = context;
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {  //拿到一个ViewHolder对象
        if (convertView == null || convertView.getTag() == null ) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public <T extends View> T getView(int viewId) {  //通过控件的Id获取对应的控件,如果没有则加入views
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    public ViewHolder setText(int resId, String text) {
        TextView tv = getView(resId);
        tv.setText(text);
        return this;
    }

    public ViewHolder setText(int resId, SpannableStringBuilder text) {
        TextView tv = getView(resId);
        tv.setText(text);
        return this;
    }


    public ViewHolder setTextColor(int resId, int color) {
        TextView tv = getView(resId);
        tv.setTextColor(color);
        return this;
    }

    public ViewHolder setImageResource(int resId, Integer imgResId) {
        ImageView iv = getView(resId);
        if (imgResId == null) {
            iv.setVisibility(View.GONE);
        } else {
            iv.setImageResource(imgResId);
        }
        return this;
    }

    public ViewHolder setBackgroundColor(int resId, int color) {
        View v = getView(resId);
        v.setBackgroundColor(color);
        return this;
    }


    public ViewHolder setShow(int resId, Boolean isShow) {
        getView(resId).setVisibility(isShow ? View.VISIBLE : View.GONE);
        return this;
    }

    public ViewHolder setVisible(int resId, Boolean isShow) {
        getView(resId).setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
        return this;
    }


}
