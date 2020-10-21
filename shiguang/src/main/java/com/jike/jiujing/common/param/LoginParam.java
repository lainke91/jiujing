package com.jike.jiujing.common.param;

import java.io.Serializable;

public class LoginParam implements Serializable {
    private String userName;
    private String password;

    public LoginParam(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
