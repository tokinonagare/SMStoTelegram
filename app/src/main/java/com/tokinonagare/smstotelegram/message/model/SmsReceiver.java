package com.tokinonagare.smstotelegram.message.model;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.tokinonagare.smstotelegram.message.IMessageView;
import com.tokinonagare.smstotelegram.message.presenter.MessagePresenterImp;

/**
 * 获取最新短信内容
 * Created by tokinonagare on 05/10/2016.
 */

public class SmsReceiver {

    private final static Uri SMS_INBOX = Uri.parse("content://sms/");

    private IMessageView messageActivity;

    public SmsReceiver(IMessageView messageActivity) {
        this.messageActivity = messageActivity;
    }

    /**
     * 获取短信内容
     */
    public String getSmsFromPhone(MessagePresenterImp.SmsObserver smsObserver) {
        String message = "短信内容";

        messageActivity.getMyContentResolver().registerContentObserver(SMS_INBOX, true, smsObserver);
        ContentResolver cr = messageActivity.getMyContentResolver();

        // 获取短信：person，发件人；address，电话号码；body，内容；
        String[] projection = new String[] {"person", "address", "body"};

        // 过滤号码和时间
        //String where = " address = '10086' AND date >  "
        //                + (System.currentTimeMillis() - 10 * 60 * 1000);
        String where = null;

        Cursor cur = cr.query(SMS_INBOX, projection, where, null, "date desc");

        if (cur != null && cur.moveToNext()) {
            String number = cur.getString(cur.getColumnIndex("address"));//手机号
            String name = cur.getString(cur.getColumnIndex("person"));//联系人姓名列表
            String body = cur.getString(cur.getColumnIndex("body"));//短信内容

            // 当通讯录中有匹配名称时显示，否则隐藏
            if (name == null) {
                message = number + " " + body;
            } else {
                message = name + " " + number + " " + body;
            }
        }
        return message;
    }
}
