package com.jike.jiujing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.jike.jiujing.base.BaseFragment;
import com.jike.jiujing.common.entry.CaptainUser;
import com.jike.jiujing.common.entry.ResultData;
import com.jike.jiujing.common.param.LoginParam;
import com.jike.jiujing.common.service.ApiLoader;
import com.jike.jiujing.common.service.Callback;
import com.jike.jiujing.common.utils.SPUtils;
import com.jike.jiujing.common.utils.ToastUtils;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sjgb8d on 2020/9/11
 */
public class LoginFragment extends BaseFragment {

    private LoginFragmentInterface listener;

    @BindView(R.id.tv_account)
    EditText tvAccount;
    @BindView(R.id.tv_password)
    EditText tvPassword;


    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listener = (LoginFragmentInterface) getActivity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @OnClick(R.id.button_login)
    void onClick() {
//        listener.onLoginClick();

        String account = tvAccount.getText().toString();
        String password = tvPassword.getText().toString();
        if(TextUtils.isEmpty(account)){
            ToastUtils.show("请输入账号");
            return;
        }
        if(TextUtils.isEmpty(password)){
            ToastUtils.show("请输入密码");
            return;
        }
        LoginParam loginParam = new LoginParam(account, password);
        new ApiLoader().login(loginParam).subscribe(new Callback<ResultData<CaptainUser>>(){
            @Override
            public void onSuccess (ResultData<CaptainUser> result) {
                if(result.isSuccess()) {
                    SPUtils.setObjectValue(currentContext, SPUtils.SP_LOGIN_DATA, result.getData());
                    listener.onLoginClick();
                } else {
                    ToastUtils.show(result.getMessage());
                }
            }
        });
    }
}
