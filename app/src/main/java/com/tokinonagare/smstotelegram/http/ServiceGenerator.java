package com.tokinonagare.smstotelegram.http;

/**
 * 网络请求基类
 * Created by tokinonagare on 05/10/2016.
 */

import com.tokinonagare.smstotelegram.BotConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;

class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    static <S> S createService(Class<S> serviceClass, Context context) {

        BotConfig botConfig = new BotConfig(context);

        // 当IP不存在；网络极差；代理服务器不存在时依然进行重连
        httpClient.retryOnConnectionFailure(true);

        //设置超时
        httpClient.connectTimeout(15, TimeUnit.SECONDS);
        httpClient.readTimeout(20, TimeUnit.SECONDS);
        httpClient.writeTimeout(20, TimeUnit.SECONDS);

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(botConfig.getBotRequestDomain())
                        .addConverterFactory(GsonConverterFactory.create());


        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
