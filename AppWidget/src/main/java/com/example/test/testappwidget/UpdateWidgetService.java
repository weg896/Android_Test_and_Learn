package com.example.test.testappwidget;

import java.util.Random;

import android.app.Application;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateWidgetService extends Service {
    private static final String TAG = "MY_UpdateWidgetService";

    @Override
    public int onStartCommand(Intent intent,int flagId, int startId) {
        Log.d(TAG, this.toString());
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.getApplicationContext());

        int[] allWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);


        for (int widgetId : allWidgetIds) {
            // create some random data
            int number = (new Random().nextInt(100));

            RemoteViews remoteViews = new RemoteViews(this.getApplicationContext().getPackageName(), R.layout.widget_layout);
            // Set the text
            remoteViews.setTextViewText(R.id.update, "Random: " + String.valueOf(number));

            // Register an onClickListener
            Intent clickIntent = new Intent(this.getApplicationContext(), MyWidgetProvider.class);

            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
        //stopSelf();

        super.onStartCommand(intent,flagId,startId);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onConfigurationChanged(Configuration newConfig){
        Log.d(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    public void onCreate(){
        Log.d(TAG, "onCreate");
        super.onCreate();
    }

    public void onDestroy(){
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    public void onLowMemory(){
        Log.d(TAG, "onLowMemory");
        super.onLowMemory();
    }

    public void onRebind(Intent intent){
        Log.d(TAG, "onRebind");
        super.onRebind(intent);
    }

    public void onTaskRemoved(Intent rootIntent){
        Log.d(TAG, "onTaskRemoved");
        super.onRebind(rootIntent);
    }

    public void onTrimMemory(int level){
        Log.d(TAG, "onTrimMemory");
        super.onTrimMemory(level);
    }

    public boolean onUnbind(Intent intent){
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }


}


