package com.tokinonagare.smstotelegram;

/**
 * BOT & 发送目标频道 信息
 * Created by tokinonagare on 05/10/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class BotConfig {

    private final SharedPreferences sharedPreferences;

    private static final String FILENAME = "group_id_and_bot_token";

    private final static String BOT_REQUEST_DOMAIN = "https://api.telegram.org/";

    public BotConfig(Context context) {
        sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    }

    public String getBotRequestDomain() {
        return BOT_REQUEST_DOMAIN + "bot" + getBotToken() + "/";
    }

    public String getChatID() {
        return sharedPreferences.getString("groupId", "");
    }

    private String getBotToken() {
        return sharedPreferences.getString("botToken", "");
    }
}
