package com.example.myhomeworkapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;

public class LongRunningService extends Service {
    private static final String TAG = "LongRunningService";

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(final Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "run: executed at "+new Date().toString());
            }
        }).start();


        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 60*1000;
        long triggerAtTime = SystemClock.elapsedRealtime()+anHour;
        Intent i = new Intent(this,AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent, flags, startId);
    }


}
