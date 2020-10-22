package com.jike.jiujing.common.entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SignInActivityList implements Serializable {
    private List<SignInActivity> activitylist = new ArrayList<>();

    public List<SignInActivity> getActivitylist() {
        return activitylist;
    }
}
