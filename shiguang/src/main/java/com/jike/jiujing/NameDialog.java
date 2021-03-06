package com.jike.jiujing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.EditText;

import com.jike.jiujing.common.utils.ToastUtils;
import com.jike.jiujing.common.view.BaseDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class NameDialog extends BaseDialog {


    @BindView(R.id.et_input)
    EditText etInput;
    public interface OnSubmitClickListener {
        void onSubmitClick(String value);
    }

    private OnSubmitClickListener onSubmitClickListener;

    public NameDialog setOnSubmitClickListener(OnSubmitClickListener listener) {
        this.onSubmitClickListener = listener;
        return this;
    }

    public NameDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_name;
    }

    @Override
    protected void initView() {
        super.initView();
        setWidthPercent(0.8f);
    }

    @OnClick(R.id.btn_submit)
    public void onSumbitClick() {
        if (onSubmitClickListener != null) {
            String value = etInput.getText().toString();
            if (TextUtils.isEmpty(value) || TextUtils.isEmpty(value.trim())) {
                ToastUtils.show( "请输入团队名称");
                return;
            }
            onSubmitClickListener.onSubmitClick(value);
            dismiss();
        }
    }
}
