package com.example.myapplication.activity;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.smtt.sdk.JsContext;
import com.tencent.smtt.sdk.QbSdk;

public class MyApplication extends Application {
    // 静态的Context对象，用于在应用程序中获取全局的Context实例
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        // 获取应用程序的Context，并赋值给静态的context变量
        context = getApplicationContext();

        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }


            @Override
            public void onViewInitFinished(boolean isX5Core) {
                //X5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败
                Log.e("MyApplication", "onViewInitFinished isX5Core:" + isX5Core);
                //成功加载X5内核后才可以创建和使用X5JsCore
                if(isX5Core) {
                    //创建X5JsCore对象并使用
                    JsContext jsContext = new JsContext(MyApplication.context);
                    jsContext.evaluateJavascript("console.log('hello')", null);
                }
            }
        });

    }

    // 静态方法，用于获取全局的Context实例
    public static Context getContext() {
        return context;
    }
}