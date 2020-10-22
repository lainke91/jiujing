package com.jike.jiujing.common.entry;

import java.io.Serializable;

public class SignInMember implements Serializable {
    private String memberID;
    private String score;
    private String memberName;

    public String getMemberID() {
        return memberID;
    }

    public String getScore() {
        return score;
    }

    public String getMemberName() {
        return memberName;
    }
}
