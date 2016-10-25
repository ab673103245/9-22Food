package org.mobiletrain.food;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by 王松 on 2016/8/3.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "828f4700eba4fb1a618fe13c35438ab4");
    }
}
