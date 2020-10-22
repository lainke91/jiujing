package com.jike.jiujing.common.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.jike.jiujing.R;
import com.jike.jiujing.common.utils.KeyboardUtils;

import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasswordDialog extends BaseDialog {

    @BindView(R.id.et_temp)
    EditText etTemp;
    @BindView(R.id.et_password) PasswordEditView etPassword;
    @BindView(R.id.lineView)
    View lineView;
    @BindView(R.id.tv_tips)
    TextView tvTips;


    public interface OnCancelClickListener {
        void onCancelClick();
    }
    private OnCancelClickListener onCancelClickListener;

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_password;
    }

    public PasswordDialog(@NotNull Context context) {
        super(context);
//        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    protected void initView() {
        super.initView();
        setWidthPercent(0.8f);
        etTemp.setFocusable(true);
        etTemp.setFocusableInTouchMode(true);
        etTemp.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        }, 220);
        etTemp.setOnEditorActionListener(new OnEditorEnterListener());
        etTemp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etPassword.setInputText(etTemp.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        setPasswordEnable(true);
    }

    public class OnEditorEnterListener implements TextView.OnEditorActionListener{
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            return (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
        }
    }

    public void setError() {
        lineView.setVisibility(View.VISIBLE);
        tvTips.setVisibility(View.VISIBLE);
        tvTips.setText(String.format("密码错误，请找裁判确认密码"));
    }

    public PasswordDialog setPasswordEnable(boolean isEnable){
        etPassword.setEnable(isEnable);
        etTemp.setEnabled(isEnable);
        if(!isEnable) {
            etTemp.setText("");
        }
        return this;
    }

    public PasswordDialog setOnPasswordCompleteListener(PasswordEditView.OnPasswordCompleteListener listener) {
        etPassword.setOnPasswordCompleteListener(listener);
        return this;
    }

    public PasswordDialog setOnCancelClickListener(OnCancelClickListener onCancelClickListener) {
        this.onCancelClickListener = onCancelClickListener;
        return this;
    }

    @OnClick(R.id.et_password)
    public void onPasswordViewClick(View v) {
        KeyboardUtils.showInputKeyboard(getContext(), etTemp);
    }

    @OnClick(R.id.iv_close)
    public void onCloseClick() {
        if(onCancelClickListener != null) {
            onCancelClickListener.onCancelClick();
        }
        dismiss();
    }
}
