package com.example.myapplication.activity.components;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.gyf.immersionbar.ImmersionBar;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity{

    private OkHttpClient okHttpClient;
    private static final String TAG = "OkhttpActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        //沉浸式处理
        ImmersionBar.with(this)
                .transparentStatusBar()  //透明状态栏，不写默认透明色
                .init();
        okHttpClient = new OkHttpClient();
        //getSync();
    }

    public void getSync(View view){
        new Thread(){
            @Override
            public void run() {
                Request request = new Request.Builder().url("http://www.httpbin.org/get?a=1&b=2").build();
                //准备好请求的Call对象
                Call call = okHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    assert response.body() != null;
                    Log.i(TAG, "getSync: " + response.body().string());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

    public void getAsync(View view){
        Request request = new Request.Builder().url("http://www.httpbin.org/get?a=1&b=2").build();
        //准备号请求的call对象
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    Log.i(TAG, "getAsync: " + response.body().string());
                }

            }
        });
    }

    public void postSync(View view){
        new Thread(){
            @Override
            public void run() {
                FormBody requestBody = new FormBody.Builder()
                        .add("a", "1")
                        .add("b", "2")
                        .build();
                Request request = new Request.Builder()
                        .url("http://www.httpbin.org/post")
                        .post(requestBody)
                        .build();
                Call call = okHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    if (response.isSuccessful()) {
                        Log.i(TAG, "postSync: " + response.body().string());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

    public void postAsync(View view){
        FormBody formBody= new FormBody.Builder().add("a", "1").add("b", "2").build();
        Request request = new Request.Builder().url("http://www.httpbin.org/post").post(formBody).build();
        //准备好请求的Call对象
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.i(TAG, "postAsync: "+response.body().string());
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });

    }




}
