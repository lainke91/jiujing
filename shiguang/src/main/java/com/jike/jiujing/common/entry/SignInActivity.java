package com.jike.jiujing.common.entry;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SignInActivity implements Serializable {
    private String activityID;
    private String activityName;
    private String activityInfo;
    private String activityDesc;
    private String activityState;
    private Drawable icon;
    private String time;
    private int energy;
    private int count;
    private int joinSize;

    private List<SignInMember> memberlist = new ArrayList<>();

    public String getActivityID() {
        return activityID;
    }

    public String getActivityName() {
        return activityName;
    }


    public List<SignInMember> getMemberlist() {
        return memberlist;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(String activityInfo) {
        this.activityInfo = activityInfo;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public String getActivityStatus() {
        return activityState;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityState = activityStatus;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMemberlist(List<SignInMember> memberlist) {
        this.memberlist = memberlist;
    }

    public String getActivityStatusCH() {
        if("0".equals(activityState)) {
            return "报名中";
        } else if("1".equals(activityState)) {
            return "已报名";
        } else if("2".equals(activityState)) {
            return "进行中";
        } else if("2".equals(activityState)) {
            return "结束";
        }
        return "";
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getJoinSize() {
        return joinSize;
    }

    public void setJoinSize(int joinSize) {
        this.joinSize = joinSize;
    }
}
