package com.jike.jiujing.common.entry;

import com.jike.jiujing.common.utils.ToastUtils;

import java.io.Serializable;

public class ResultBase implements Serializable {
    public static final String SUCCESS_CODE = "1";

    private String resultCode;
    private String message;

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        if(SUCCESS_CODE.equals(resultCode)){
            return true;
        }
        ToastUtils.show(message);
        return false;
    }
}
