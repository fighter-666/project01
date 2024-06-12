// IAppAidlInterface.aidl
package com.example.myapplication;

// Declare any non-default types here with import statements
import com.example.myapplication.IResponseCallback;

interface IAppAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void pay();
    void sendMessage(String message, IResponseCallback callback);
}