package com.jike.jiujing;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jike.jiujing.common.entry.CaptainUser;
import com.jike.jiujing.common.service.LogInterceptor;
import com.jike.jiujing.common.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;

/**
 * Created by Artem Kholodnyi on 11/2/15.
 */
public class App extends Application {
    private static App app;

    private Retrofit retrofit;
    private static String BASE_URL = "https://apigatewayqa.sgmlink.com:3221/service/";

    private CaptainUser user;

    @Override
    public void onCreate() {
        super.onCreate();
        //Fabric.with(this, new Crashlytics());
        app = this;

        Timber.plant(new Timber.DebugTree());
        initRetrofit();
    }

    public static App getInstance(){
        return app;
    }

    public static Context getAppContext() {
        return app;
    }

    private void initRetrofit() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(new LogInterceptor());

        okHttpBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(30, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(30, TimeUnit.SECONDS);
        //错误重连
        okHttpBuilder.retryOnConnectionFailure(true);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
        retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public CaptainUser getUser() {
        if(user == null) {
            user = (CaptainUser) SPUtils.getObjectValue(this, SPUtils.SP_LOGIN_DATA, CaptainUser.class);
        }
        return user;
    }

    public void setUser(CaptainUser user) {
        this.user = user;
        SPUtils.setObjectValue(this, SPUtils.SP_LOGIN_DATA, user);
    }
}
