package com.atguiu.mybili;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by 进击的程序猿 on 2017/3/21.
 */

public class MyApplication extends Application {

    private static Context context;
    private static Thread mainThread;
    private static int  threadid;
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        threadid = android.os.Process.myPid();
        handler = new Handler();

        //初始化未捕获异常 上线的时候才打开
        //CrashHandler.getInstance().init();
    }

    public static Context getContext() {
        return context;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static int getThreadid() {
        return threadid;
    }

    public static Handler getHandler() {
        return handler;
    }
}
