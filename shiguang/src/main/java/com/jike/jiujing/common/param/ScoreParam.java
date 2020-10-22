package com.jike.jiujing.common.param;

import com.jike.jiujing.common.entry.SignInMember;

import java.util.ArrayList;
import java.util.List;

public class ScoreParam {
    private String activityID;
    private List<SignInMember> scoreList = new ArrayList<>();

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public void setMemberList(List<SignInMember> memberList) {
        this.scoreList = memberList;
    }
}
