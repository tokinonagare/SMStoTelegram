package com.tokinonagare.smstotelegram.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.tokinonagare.smstotelegram.R;
import com.tokinonagare.smstotelegram.service.ReceiverService;

/**
 * Created by tokinonagare on 05/10/2016.
 */

public class MessageActivity extends AppCompatActivity {

    private EditText editor_groupId, editor_botToken;

    private static final String FILENAME = "group_id_and_bot_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);

        editor_groupId = (EditText) findViewById(R.id.groupId);
        editor_botToken = (EditText) findViewById(R.id.botToken);

        showSavedConfig();


        // 启动 短信 & 电话 接收服务
        Intent startIntent = new Intent(this, ReceiverService.class);
        startService(startIntent);
    }

    /**
    * 显示已经保存的配置信息
    */
    private void showSavedConfig() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(FILENAME, MODE_WORLD_READABLE);

        String saved_groupId = sharedPreferences.getString("groupId", "");
        String saved_botToken = sharedPreferences.getString("botToken", "");

        editor_groupId.setText(saved_groupId);
        editor_botToken.setText(saved_botToken);
    }

    /**
     * 保存配置信息
     * @param view
     */
    public void saveConfig(View view) {

        Editor editor = getSharedPreferences(FILENAME, MODE_WORLD_READABLE).edit();

        String groupId = editor_groupId.getText().toString();
        String botToken = editor_botToken.getText().toString();

        editor.putString("groupId", groupId);
        editor.putString("botToken", botToken);

        editor.apply();
    }
}