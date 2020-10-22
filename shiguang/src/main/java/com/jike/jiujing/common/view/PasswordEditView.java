package com.jike.jiujing.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputFilter;
import android.util.AttributeSet;

import com.jike.jiujing.R;
import com.jike.jiujing.common.utils.SizeUtils;

import java.lang.ref.WeakReference;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class PasswordEditView extends AppCompatTextView {

    public interface OnPasswordCompleteListener {
        void onComplete(String password);
    }
    private OnPasswordCompleteListener onPasswordCompleteListener;


    private int passwordLength = 6;

    private Paint passwordPaint = new Paint(ANTI_ALIAS_FLAG);
    private Paint borderPaint = new Paint(ANTI_ALIAS_FLAG);
    private int textLength;

    private RectF[] rectItem;
    private float itemWidth;
    private float itemRadius;
    private int itemBackground;
    private int itemNotEnableBackground;


    private float itemMargin;
    private float itemTextSize;
    private int itemTextColor;
    private boolean isVisibleCharacter = true;
    private boolean isEnable = true;


    public void setOnPasswordCompleteListener(OnPasswordCompleteListener onPasswordCompleteListener) {
        this.onPasswordCompleteListener = onPasswordCompleteListener;
    }

    public void setVisibleCharacter(boolean visibleCharacter) {
        isVisibleCharacter = visibleCharacter;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
        if(isEnable){
            borderPaint.setColor(itemBackground);
        } else {
            borderPaint.setColor(itemNotEnableBackground);
        }
        invalidate();
    }

    private VisibleHandler handler;


    public PasswordEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PasswordEditView);
        itemWidth = ta.getDimension(R.styleable.PasswordEditView_password_item_width, SizeUtils.dp2px(context, 30));
        itemRadius = ta.getDimension(R.styleable.PasswordEditView_password_item_radius, SizeUtils.dp2px(context, 4));
        itemBackground = ta.getColor(R.styleable.PasswordEditView_password_item_background, Color.WHITE);
        itemNotEnableBackground = ta.getColor(R.styleable.PasswordEditView_password_item_not_enable, Color.parseColor("#CCCCCC"));
        itemMargin = ta.getDimension(R.styleable.PasswordEditView_password_item_margin, SizeUtils.dp2px(context, 12));
        itemTextSize = ta.getDimensionPixelSize(R.styleable.PasswordEditView_password_item_text_size, SizeUtils.sp2px(context, 14));
        itemTextColor = ta.getColor(R.styleable.PasswordEditView_password_item_text_color, Color.BLACK);
        ta.recycle();
        initView();
    }

    private void initView() {
        handler = new VisibleHandler(this);
        setBackgroundColor(Color.TRANSPARENT);
        borderPaint.setColor(itemBackground);

        passwordPaint.setStyle(Paint.Style.FILL);
        passwordPaint.setTextAlign(Paint.Align.CENTER);
        passwordPaint.setColor(itemTextColor);
        passwordPaint.setTextSize(itemTextSize);

//        this.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(passwordLength)});
        rectItem = new RectF[passwordLength];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < passwordLength; i++) {
            float left = i * (itemWidth + itemMargin);
            rectItem[i] = new RectF(left, itemRadius * 2, left + itemWidth, getHeight() + itemRadius * 2);
            canvas.drawRoundRect(rectItem[i], itemRadius, itemRadius, borderPaint);
        }
        for (int i = 0; i < textLength; i++) {
            float cx = rectItem[i].left + rectItem[i].width() / 2;
            float cy = rectItem[i].height() / 2;

            if(i == textLength - 1 && isVisibleCharacter) {
                canvas.drawText(getText().toString().substring(textLength - 1, textLength), cx, cy + itemTextSize / 2 + itemRadius, passwordPaint);
            } else {
                canvas.drawText("*", cx, cy + itemTextSize / 2 + itemRadius, passwordPaint);
            }
        }
    }

    public void setInputText(String text) {
        setText(text);
        if(textLength >= text.length()) {  //删除字符
            isVisibleCharacter = false;
        } else {
            isVisibleCharacter = true;
        }
        this.textLength = text.length();
        handler.removeCallbacksAndMessages(null);
        handler.sendEmptyMessageDelayed(0, 1000);
        invalidate();
        if(onPasswordCompleteListener != null && textLength == passwordLength) {
            onPasswordCompleteListener.onComplete(text);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacksAndMessages(null);
    }


    public static class VisibleHandler extends Handler {
        private WeakReference<PasswordEditView> reference;

        public VisibleHandler(PasswordEditView view) {
            reference = new WeakReference(view);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PasswordEditView view = reference.get();
            if(view != null) {
                view.setVisibleCharacter(false);
                view.invalidate();
            }
        }
    }
}

