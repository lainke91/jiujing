package com.jike.jiujing.common.entry;

import java.io.Serializable;

public class ResultBase implements Serializable {
    public static final String SUCCESS_CODE = "1";

    private String resultCode;
    private String message;

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(resultCode);
    }
}
