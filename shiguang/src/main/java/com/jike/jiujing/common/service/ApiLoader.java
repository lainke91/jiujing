package com.jike.jiujing.common.service;

import com.jike.jiujing.App;
import com.jike.jiujing.common.entry.CaptainUser;
import com.jike.jiujing.common.entry.ResultData;
import com.jike.jiujing.common.entry.SignInActivityList;
import com.jike.jiujing.common.param.LoginParam;
import com.jike.jiujing.common.param.TaskParam;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiLoader {

    private ApiService service;

    public ApiLoader() {
        service = App.getInstance().getRetrofit().create(ApiService.class);
    }

    public Observable<ResultData<CaptainUser>> login(LoginParam param) {
        return observe(service.login(param));
    }


    public Observable<ResultData> changeTeamName(CaptainUser param) {
        return observe(service.changeTeamName(param));
    }

    public Observable<ResultData<CaptainUser>> taskFinish(TaskParam param) {
        return observe(service.taskFinish(param));
    }

    public Observable<ResultData<SignInActivityList>> getActivity(String teamID) {
        TaskParam param = new TaskParam();
        param.setTeamID(teamID);
        return observe(service.getActivity(param));
    }




    protected <T> Observable<T> observe(Observable<T> observable){
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



}
