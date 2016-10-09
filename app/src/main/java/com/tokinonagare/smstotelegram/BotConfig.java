package com.tokinonagare.smstotelegram;

/**
 * BOT & 发送目标频道 信息
 * Created by tokinonagare on 05/10/2016.
 */

public class BotConfig {

    private final static String CHAT_ID = "";// 频道ID

    private final static String BOT_TOKEN = "";// 机器人Token
    private final static String BOT_REQUEST_DOMAIN = "https://api.telegram.org/bot"+ BOT_TOKEN + "/";


    public static String getChatId() {
        return CHAT_ID;
    }

    public static String getBotRequestDomain() {
        return BOT_REQUEST_DOMAIN;
    }
}
