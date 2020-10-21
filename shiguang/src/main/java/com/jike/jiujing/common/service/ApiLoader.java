package com.jike.jiujing.common.service;

import com.jike.jiujing.App;
import com.jike.jiujing.common.entry.CaptainUser;
import com.jike.jiujing.common.entry.ResultData;
import com.jike.jiujing.common.param.LoginParam;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiLoader {

    private ApiService service;

    public ApiLoader() {
        service = App.getInstance().getRetrofit().create(ApiService.class);
    }

    public Observable<ResultData<CaptainUser>> login(LoginParam loginParam) {
        return observe(service.login(loginParam));
    }

    protected <T> Observable<T> observe(Observable<T> observable){
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



}
