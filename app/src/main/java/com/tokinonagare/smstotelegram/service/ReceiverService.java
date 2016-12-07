package com.tokinonagare.smstotelegram.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.tokinonagare.smstotelegram.R;
import com.tokinonagare.smstotelegram.receiver.CallReceiver;
import com.tokinonagare.smstotelegram.receiver.SmsReceiver;

/**
 * Created by tokinonagare on 02/11/2016.
 */

public class ReceiverService extends Service {

    private final static String TAG = ReceiverService.class.getSimpleName();

    private final static int FOREGROUND_ID = 1001;

    @Override
    public void onCreate() {
        startReceiver();
    }

    // 前台service白色保护
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "WhiteService->onStartCommand");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("SmsToTelegram");
        builder.setContentText("I am a Sms && Call Receive service");
        builder.setWhen(System.currentTimeMillis());
        Notification notification = builder.build();
        startForeground(FOREGROUND_ID, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Intent restartService = new Intent(this, ReceiverService.class);
        startService(restartService);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startReceiver() {

        // 开启来电监听服务
        CallReceiver callReceiver = new CallReceiver(this);
        callReceiver.getCallFromPhone();

        // 开启短信监听服务
        SmsReceiver smsReceiver = new SmsReceiver(this);
        smsReceiver.getSmsFromPhone();
    }
}
