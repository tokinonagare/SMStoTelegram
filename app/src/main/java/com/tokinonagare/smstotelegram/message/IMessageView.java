package com.tokinonagare.smstotelegram.message;

import android.content.ContentResolver;

/**
 * Created by tokinonagare on 05/10/2016.
 */
public interface IMessageView {
    ContentResolver getMyContentResolver();

    void setMessageContent(String message);

    void setMessageSendStatus(String status);
}
