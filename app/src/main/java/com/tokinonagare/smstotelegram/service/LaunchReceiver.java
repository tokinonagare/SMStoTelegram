package com.tokinonagare.smstotelegram.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by tokinonagare on 07/12/2016.
 */

public class LaunchReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent serviceIntent = new Intent(context , ReceiverService.class);
        // 启动指定Server
        context.startService(serviceIntent);
    }
}
