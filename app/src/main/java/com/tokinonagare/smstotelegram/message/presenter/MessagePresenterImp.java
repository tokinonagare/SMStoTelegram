package com.tokinonagare.smstotelegram.message.presenter;

import android.util.Log;

import com.google.gson.JsonObject;
import com.tokinonagare.smstotelegram.BotConfig;
import com.tokinonagare.smstotelegram.http.HttpCallBack;
import com.tokinonagare.smstotelegram.http.HttpRequest;
import com.tokinonagare.smstotelegram.http.IHttpCallBack;

/**
 * 向BOT发送消息
 * Created by tokinonagare on 05/10/2016.
 */

public class MessagePresenterImp implements IMessagePresenter {

    private final static String chatId = BotConfig.getChatId();

    @Override
    public void sendMessage(String message) {

        // 发送短信
        HttpRequest httpRequest = new HttpRequest();
        IHttpCallBack httpCallBack = GeneratorCallBack();
        httpRequest.sendMessage(chatId, message, httpCallBack);

    }

    private IHttpCallBack GeneratorCallBack() {
        return new HttpCallBack() {

            @Override
            public void onSuccess(JsonObject jsonObject) {
                super.onSuccess(jsonObject);
                boolean ok = jsonObject.get("ok").getAsBoolean();
                if (!ok) {
                    int errorCode = jsonObject.get("error_code").getAsInt();
                    String description = jsonObject.get("description").getAsString();
                    String err = "error_code: " + errorCode + " description: " + description;
                    Log.d("SendMessageFailed ", err);
                }
            }

            @Override
            public void onFailed(String err) {
                super.onFailed(err);
                Log.d("SendMessageFailed ", err);
            }
        };
    }
}
