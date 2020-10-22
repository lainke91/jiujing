package com.jike.jiujing.common.param;

import com.jike.jiujing.common.entry.SignInMember;

import java.util.ArrayList;
import java.util.List;

public class SignInParam {
    private String teamID;
    private String activityID;
    private int energy;
    private List<SignInMember> memberList = new ArrayList<>();

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setMemberList(List<SignInMember> memberList) {
        this.memberList = memberList;
    }
}
