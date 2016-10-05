package com.tokinonagare.smstotelegram.http;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 网络请求的API细节
 * Created by tokinonagare on 05/10/2016.
 */

interface IHttpRequest {

    @GET("sendMessage")
    Call<JsonObject> sendMessageServiceCreate(
            @Query("chat_id") String chatId,
            @Query("text") String message);
}
