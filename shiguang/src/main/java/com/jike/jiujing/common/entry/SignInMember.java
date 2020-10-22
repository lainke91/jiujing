package com.jike.jiujing.common.entry;

import java.io.Serializable;

public class SignInMember implements Serializable {
    private String teamID;
    private String memberID;
    private String score;
    private String memberName;

    public SignInMember(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getScore() {
        return score;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
