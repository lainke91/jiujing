package com.jike.jiujing.common.service;

import com.jike.jiujing.common.utils.ToastUtils;

import io.reactivex.observers.DisposableObserver;

public class Callback <T> extends DisposableObserver<T> {

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);

    }

    @Override
    public void onError(Throwable e) {
        onFailure();
    }

    @Override
    public void onComplete() {

    }

    public void onSuccess(T t){

    }

    public void onFailure(){
        ToastUtils.show("网络连接失败，请稍后再试");
    }
}
