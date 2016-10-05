package com.tokinonagare.smstotelegram.http;

import com.google.gson.JsonObject;

/**
 * 网络请求回调接口
 * Created by tokinonagare on 05/10/2016.
 */

public interface IHttpCallBack {
    void onSuccess(JsonObject jsonObject);

    void onFailed(String err);
}
