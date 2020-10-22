package com.jike.jiujing.common.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class BasePopup extends PopupWindow {

    public static int M = ViewGroup.LayoutParams.MATCH_PARENT;
    public static int W = ViewGroup.LayoutParams.WRAP_CONTENT;

    public static float RECOMMEND_ALPHA = 0.6f;

    protected Context context;
    protected View view;
    private boolean isAlpha = false;

    public interface OnPopupItemClickListener {
        void onPopupItemClick(int pos);
    }
    protected OnPopupItemClickListener onPopupItemClickListener;
    public void setOnPopupItemClickListener(OnPopupItemClickListener listener) {
        this.onPopupItemClickListener = listener;
    }

    public BasePopup(Context context, int resId) {
        super(context);
        this.context = context;

        this.setFocusable(true);  //设置弹出窗体可点击
        setBackgroundDrawable(new ColorDrawable());
        this.view = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(resId, null);
        this.setContentView(view);
        setSize(M, W);
        //因为某些机型是虚拟按键的,所以要加上以下设置防止挡住按键
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    public void setSize(int w,int h){
        this.setWidth(w);
        this.setHeight(h);
    }

    public void setRootViewAlpha(float alpha){
        isAlpha = true;
        WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
        lp.alpha = alpha;
        ((Activity)context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity)context).getWindow().setAttributes(lp);
    }

    public void showBottom(){
        isAlpha = false;
        showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    public void showBottom(float alpha){
        if(alpha > 0 && alpha < 1) {
            isAlpha = true;
            setRootViewAlpha(alpha);
        }else {
            isAlpha = false;
        }
        showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void dismiss() {
        if(isAlpha) {
            setRootViewAlpha(1.0f);
        }
        super.dismiss();
    }

    public void touchOutsideDismiss(final View childView){
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int x=(int) event.getX();
                int y=(int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (x < childView.getLeft() || x > childView.getRight() ||
                            y < childView.getTop() || y > childView.getBottom()) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }


}
