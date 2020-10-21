package com.jike.jiujing.common.entry;

import java.io.Serializable;

public class Member implements Serializable {
    private String memberId;  //成员ID
    private String memberName;  //成员名

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }
}
