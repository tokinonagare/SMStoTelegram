package com.tokinonagare.smstotelegram.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tokinonagare.smstotelegram.message.model.SmsReceiver;

/**
 * Created by tokinonagare on 02/11/2016.
 */

public class ReceiverService extends Service {

    private final static int GRAY_SERVICE_ID = 1001;

    @Override
    public void onCreate() {
        startReceiver();
    }

    // 前台service灰色保护
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT < 18) {
            startForeground(GRAY_SERVICE_ID, new Notification());
        } else {
            Intent innerIntent = new Intent(this, GrayInnerService.class);
            startService(innerIntent);
            startForeground(GRAY_SERVICE_ID, new Notification());
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public static class GrayInnerService extends Service {

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(GRAY_SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

    }

    @Override
    public void onDestroy() {

        Intent restartService = new Intent(this, ReceiverService.class);
        this.startService(restartService);

        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startReceiver() {

        // 开启短信监听服务
        SmsReceiver smsReceiver = new SmsReceiver(this);
        smsReceiver.getSmsFromPhone();
    }
}
