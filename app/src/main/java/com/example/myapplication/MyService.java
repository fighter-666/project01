package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    public MyService() {
        Log.d(TAG, "MyService");
    }

    class MyBinder extends IAppAidlInterface.Stub {

        @Override
        public void pay() {
            Log.d("MyService", "Successfully paid");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MyService", "onBind called");
        //throw new UnsupportedOperationException("Not yet implemented");
        return new MyBinder();
    }
}