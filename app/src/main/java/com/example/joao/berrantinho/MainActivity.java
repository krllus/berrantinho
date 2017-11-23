package com.example.joao.berrantinho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.joao.berrantinho.notification.NotificationHelper;
import com.example.joao.berrantinho.notification.NotificationIntentService;
import com.example.joao.berrantinho.notification.model.NotificationBase;
import com.example.joao.berrantinho.notification.model.SuplementacaoNotification;
import com.example.joao.berrantinho.notification.model.SuplementacaoNotificationBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtChannelOneText;

    private NotificationHelper notificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationHelper = new NotificationHelper(this);
        edtChannelOneText = findViewById(R.id.channel_one_text);

        Button btnChannelOnePost = findViewById(R.id.post_to_channel_one);
        Button btnChannelOneSettings = findViewById(R.id.channel_one_settings);

        btnChannelOnePost.setOnClickListener(this);
        btnChannelOneSettings.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_settings:
                notificationHelper.openNotificationSettings();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.post_to_channel_one:
                postNotification(
                        NotificationBase.NOTIFICATION_ID_SUPLEMENTACAO,
                        getChannelOneText());
                break;
            case R.id.channel_one_settings:
                notificationHelper.openNotificationSettings(NotificationBase.NOTIFICATION_CHANNEL_SUPLEMENTACAO_ID);
                break;

            case R.id.launchActivity2:
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                break;
        }
    }

    private String getChannelOneText() {
        if (edtChannelOneText != null) {
            return edtChannelOneText.getText().toString();
        }
        return "";
    }

    private void postNotification(int id, String content) {
        switch (id) {
            case NotificationBase.NOTIFICATION_ID_SUPLEMENTACAO:
                SuplementacaoNotification notification = new SuplementacaoNotificationBuilder()
                        .setMessageContent(content)
                        .setMessageTitle("Title Notification Suplementacao")
                        .createSuplementacaoNotification();

                NotificationIntentService.startNotification(this,
                        notification,
                        notification.getID(),
                        NotificationBase.NOTIFICATION_CHANNEL_SUPLEMENTACAO_ID);
                break;
        }
    }


}
