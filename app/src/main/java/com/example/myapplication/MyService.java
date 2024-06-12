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

        @Override
        public void sendMessage(String message, IResponseCallback callback) throws RemoteException {
            Log.d("MyService", "Received message: " + message);
            try {
                if (callback != null){
                    callback.onResponse("Received: " + message);
                }
            } catch (RuntimeException e ){
                Log.d(TAG,"Error responding", e);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MyService", "onBind called");
        //throw new UnsupportedOperationException("Not yet implemented");
        return (IBinder) new MyBinder();
        //return null;
    }
}