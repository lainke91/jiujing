package com.jike.jiujing.common.entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CaptainUser implements Serializable {
    private String teamID;  //队伍ID，0代表裁判组
    private String teamName;  //队名
    private String teamEnergy;  //队伍能量
    private String teamLock;  //团队任务解锁，1解锁、0未解锁
    private List<Member> member = new ArrayList<>();  //成员列表
    private List<Task> task = new ArrayList<>();  //任务列表

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamID() {
        return teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamEnergy() {
        return teamEnergy;
    }

    public String getTeamLock() {
        return teamLock;
    }

    public List<Member> getMember() {
        return member;
    }

    public List<Task> getTask() {
        return task;
    }
}
