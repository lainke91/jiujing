package com.jike.jiujing.common.entry;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class SignIn implements Serializable {
    private String activityID;
    private String activityName;
    private String activityInfo;
    private String activityDesc;
    private String activityStatus;
    private Drawable icon;
    private String time;

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setActivityInfo(String activityInfo) {
        this.activityInfo = activityInfo;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getActivityID() {
        return activityID;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActivityInfo() {
        return activityInfo;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getTime() {
        return time;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }


}
