package com.jike.jiujing.common.entry;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Task implements Serializable {

    private String taskId;  //任务ID
    private String taskTitle;  //任务名
    private String taskType;  //任务类型：1个人任务、0团队任务
    private Drawable taskImg;  //任务图片
    private String taskState;  //任务状态：1已完成、0未完成
    private String password;

    public Task(String taskId, String taskTitle, String password, Drawable taskImg, String taskState) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.password = password;
        this.taskImg = taskImg;
        this.taskState = taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getTaskState() {
        return taskState;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public Drawable getTaskImg() {
        return taskImg;
    }

    public String getPassword() {
        return password;
    }
}
