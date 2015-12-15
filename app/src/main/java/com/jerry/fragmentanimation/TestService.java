package com.jerry.fragmentanimation;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {

    public class TestBinder extends Binder {
        TestService getService(){
            return TestService.this;
        }
    }

    private final IBinder testBinder = new TestBinder();

    public double random = Math.random();
    /////////////////////////////////////////////

    private final static String TAG = "TEST_SERVICE";

    public void onCreate(){
        super.onCreate();
        Log.d(TAG,"onCrate called ++ "+random + " "+this);
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d(TAG, "onStartCommand called ++ "+random + " "+this);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent arg0) {
        Log.d(TAG, "onBind called ++ "+random + " "+this);
        return testBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind called ++ "+random + " "+this);
        return true;
    }
    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind called ++ "+random + " "+this);
    }

}