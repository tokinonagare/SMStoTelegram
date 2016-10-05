package com.tokinonagare.smstotelegram.http;

import com.google.gson.JsonObject;

/**
 * 网络请求回调方法
 * Created by tokinonagare on 05/10/2016.
 */

public class HttpCallBack implements IHttpCallBack{

    @Override
    public void onSuccess(JsonObject jsonObject) {}

    @Override
    public void onFailed(String err) {}
}
