package com.tokinonagare.smstotelegram.http;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 网络请求具体实现
 * Created by tokinonagare on 05/10/2016.
 */

public class HttpRequest {


    public void sendMessage(String chatId, String message, final IHttpCallBack callBack, Context context) {

        IHttpRequest basicServer = ServiceGenerator.createService(IHttpRequest.class, context);

        Call<JsonObject> call = basicServer.sendMessageServiceCreate(chatId, message);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject jsonObject = response.body();
                callBack.onSuccess(jsonObject);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("Debug", "onFailure: " + t.getMessage());
                callBack.onFailed("发送失败，请检查网络和Vpn情况");
            }
        });
    }
}
