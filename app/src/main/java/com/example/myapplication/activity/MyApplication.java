package com.example.myapplication.activity;


import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    // 静态的Context对象，用于在应用程序中获取全局的Context实例
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        // 获取应用程序的Context，并赋值给静态的context变量
        context = getApplicationContext();
    }

    // 静态方法，用于获取全局的Context实例
    public static Context getContext() {
        return context;
    }
}