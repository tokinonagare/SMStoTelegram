package com.tokinonagare.smstotelegram.message.model;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.tokinonagare.smstotelegram.message.MessageActivity;
import com.tokinonagare.smstotelegram.message.presenter.IMessagePresenter;
import com.tokinonagare.smstotelegram.message.presenter.MessagePresenterImp;

/**
 * 监听来电
 * Created by tokinonagare on 12/10/2016.
 */

public class CallReceiver  {

    private MessageActivity messageActivity;

    public CallReceiver(MessageActivity messageActivity) {
        this.messageActivity = messageActivity;
    }

    public void getCallFromPhone() {
        MyPhoneSateListener myPhoneSateListener = new MyPhoneSateListener();
        TelephonyManager telephonyManager = (TelephonyManager) messageActivity.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(myPhoneSateListener, PhoneStateListener.LISTEN_CALL_STATE);

    }

    private class MyPhoneSateListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
           super.onCallStateChanged(state, incomingNumber);
            // 当有新来电时发送该来电信息
            if (state == TelephonyManager.CALL_STATE_RINGING) {
                IMessagePresenter messagePresenterImp = new MessagePresenterImp();
                String message = "新来电! " + incomingNumber;
                messagePresenterImp.sendMessage(message);
            }
        }
    }
}
