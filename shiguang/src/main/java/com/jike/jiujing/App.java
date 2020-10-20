package com.jike.jiujing;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;

//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;

/**
 * Created by Artem Kholodnyi on 11/2/15.
 */
public class App extends Application {
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        //Fabric.with(this, new Crashlytics());
        app = this;

        Timber.plant(new Timber.DebugTree());
    }

    public static Context getAppContext() {
        return app;
    }
}
