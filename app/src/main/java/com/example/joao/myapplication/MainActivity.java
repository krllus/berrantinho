package com.example.joao.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.joao.myapplication.notification.NotificationHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int notification_one = 101;
    private static final int notification_two = 201;

    private EditText edtChannelOneText;
    private EditText edtChannelTwoText;

    private NotificationHelper notificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationHelper = new NotificationHelper(this);

        edtChannelOneText = findViewById(R.id.channel_one_text);
        edtChannelTwoText = findViewById(R.id.channel_two_text);

        Button btnChannelOnePost = findViewById(R.id.post_to_channel_one);
        Button btnChannelTwoPost = findViewById(R.id.post_to_channel_two);
        Button btnChannelOneSettings = findViewById(R.id.channel_one_settings);
        Button btnChannelTwoSettings = findViewById(R.id.channel_two_settings);

        btnChannelOnePost.setOnClickListener(this);
        btnChannelTwoPost.setOnClickListener(this);
        btnChannelOneSettings.setOnClickListener(this);
        btnChannelTwoSettings.setOnClickListener(this);
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
                postNotification(notification_one, getChannelOneText());
                break;
            case R.id.post_to_channel_two:
                postNotification(notification_two, getChannelTwoText());
                break;
            case R.id.channel_one_settings:
                notificationHelper.openNotificationSettings(NotificationHelper.CHANNEL_ONE_ID);
                break;
            case R.id.channel_two_settings:
                notificationHelper.openNotificationSettings(NotificationHelper.CHANNEL_TWO_ID);
                break;
        }
    }

    private String getChannelOneText() {
        if (edtChannelOneText != null) {
            return edtChannelOneText.getText().toString();
        }
        return "";
    }

    private String getChannelTwoText() {
        if (edtChannelTwoText != null) {
            return edtChannelTwoText.getText().toString();
        }
        return "";
    }

    private void postNotification(int id, String title) {
        NotificationCompat.Builder notificationBuilder = null;
        switch (id) {
            case notification_one:
                notificationBuilder = notificationHelper
                        .getNotification1(title, getString(R.string.channel_one_body));
                break;
            case notification_two:
                notificationBuilder = notificationHelper
                        .getNotification2(title, getString(R.string.channel_two_body));
                break;
        }

        if (notificationBuilder != null) {
            notificationHelper.notify(id, notificationBuilder);
        }
    }


}
