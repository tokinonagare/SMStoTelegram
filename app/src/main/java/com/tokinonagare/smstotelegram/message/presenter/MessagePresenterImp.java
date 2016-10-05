package com.tokinonagare.smstotelegram.message.presenter;

import com.google.gson.JsonObject;
import com.tokinonagare.smstotelegram.BotConfig;
import com.tokinonagare.smstotelegram.http.HttpCallBack;
import com.tokinonagare.smstotelegram.http.HttpRequest;
import com.tokinonagare.smstotelegram.http.IHttpCallBack;
import com.tokinonagare.smstotelegram.message.IMessageView;
import com.tokinonagare.smstotelegram.message.model.SmsReceiver;

/**
 * 向BOT发送消息
 * Created by tokinonagare on 05/10/2016.
 */

public class MessagePresenterImp {

    private final static String chatId = BotConfig.getChatId();

    private IMessageView messageView;

    public MessagePresenterImp(IMessageView messageView) {
        this.messageView = messageView;
    }

    public void sendMessage() {
        // 显示短信发送情况
        messageView.setMessageSendStatus("短信发送中，请稍等");

        SmsReceiver smsReceiver = new SmsReceiver(messageView);
        String message = smsReceiver.getSmsFromPhone();

        HttpRequest httpRequest = new HttpRequest();
        IHttpCallBack httpCallBack = GeneratorCallBack();

        // 发送短信
        httpRequest.sendMessage(chatId, message, httpCallBack);

        // 显示最新的短信内容
        messageView.setMessageContent(message);
    }

    private IHttpCallBack GeneratorCallBack() {
        return new HttpCallBack() {

            @Override
            public void onSuccess(JsonObject jsonObject) {
                super.onSuccess(jsonObject);
                boolean ok = jsonObject.get("ok").getAsBoolean();
                if (ok) {
                    messageView.setMessageSendStatus("短信发送成功！");
                } else {
                    int errorCode = jsonObject.get("error_code").getAsInt();
                    String description = jsonObject.get("description").getAsString();
                    String err = "error_code: " + errorCode + " description: " + description;
                    messageView.setMessageSendStatus(err);
                }
            }

            @Override
            public void onFailed(String err) {
                super.onFailed(err);
                messageView.setMessageSendStatus(err);
            }
        };
    }
}
