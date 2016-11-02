package com.tokinonagare.smstotelegram.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tokinonagare.smstotelegram.R;
import com.tokinonagare.smstotelegram.message.model.CallReceiver;
import com.tokinonagare.smstotelegram.message.model.SmsReceiver;
import com.tokinonagare.smstotelegram.message.presenter.MessagePresenterImp;
import com.tokinonagare.smstotelegram.message.service.ReceiverService;

/**
 * Created by tokinonagare on 05/10/2016.
 */

public class MessageActivity extends AppCompatActivity {

    private MessagePresenterImp messagePresenterImp = new MessagePresenterImp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);

        Intent startIntent = new Intent(this, ReceiverService.class);
        startService(startIntent);

        // 开启来电监听服务
        CallReceiver callReceiver = new CallReceiver(this);
        callReceiver.getCallFromPhone();
    }

    /**
     * 手动发送短信
     */
    public void sendMessage(View view) {
        // 获取短信内容
        SmsReceiver smsReceiver = new SmsReceiver(this);
        String message = smsReceiver.getSmsFromPhone();

        messagePresenterImp.sendMessage(message);
    }
}
