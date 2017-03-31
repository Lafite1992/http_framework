package com.wzw.http_framework;

import android.app.Application;
import android.content.Context;

/**
 *
 * Created by Henry on 2017/3/31.
 */

public class HttpApplication extends Application {
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }
    public static Context getContext(){
        return sContext;
    }
}
