package com.tokinonagare.smstotelegram.message;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.tokinonagare.smstotelegram.R;
import com.tokinonagare.smstotelegram.message.presenter.MessagePresenterImp;

/**
 * Created by tokinonagare on 05/10/2016.
 */

public class MessageActivity extends AppCompatActivity implements IMessageView {

    private TextView messageContent;
    private TextView messageSendStatus;
    private MessagePresenterImp messagePresenterImp = new MessagePresenterImp(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);
        messageContent = (TextView) findViewById(R.id.message_text);
        messageSendStatus = (TextView) findViewById(R.id.message_send_status);

    }

    /**
     * 手动发送短信
     */
    public void sendMessage(View view) {
        messagePresenterImp.sendMessage();
    }

    /**
     * 显示短信内容
     */
    @Override
    public void setMessageContent(String message) {
        messageContent.setText(message);
    }

    /**
     * 显示短信发送状态
     */
    @Override
    public void setMessageSendStatus(String status) {
        messageSendStatus.setText(status);
    }

    @Override
    public ContentResolver getMyContentResolver() {
        return getContentResolver();
    }

    @Override
    public SharedPreferences getMessagePreference() {
        return getSharedPreferences("messageName", Context.MODE_PRIVATE);
    }
}
