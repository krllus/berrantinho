package com.example.joao.myapplication.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.joao.myapplication.R;

public class NotificationHelper extends ContextWrapper {
    private static final String LOG_TAG = NotificationHelper.class.getSimpleName();
    private NotificationManager notificationManager;
    public static final String CHANNEL_ONE_ID = "com.example.joao.myapplication.ONE";
    public static final String CHANNEL_ONE_NAME = "Channel One";
    public static final String CHANNEL_TWO_ID = "com.example.joao.myapplication.TWO";
    public static final String CHANNEL_TWO_NAME = "Channel Two";

    //Create your notification channels//
    public NotificationHelper(Context context) {
        super(context);
        createChannels();
    }

    void createChannels() {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        NotificationChannel notificationChannel = new NotificationChannel(
                CHANNEL_ONE_ID,
                CHANNEL_ONE_NAME,
                NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setShowBadge(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager().createNotificationChannel(notificationChannel);

        NotificationChannel notificationChannel2 = new NotificationChannel(
                CHANNEL_TWO_ID,
                CHANNEL_TWO_NAME,
                NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel2.enableLights(false);
        notificationChannel2.enableVibration(true);
        notificationChannel2.setLightColor(Color.RED);
        notificationChannel2.setShowBadge(false);
        getManager().createNotificationChannel(notificationChannel2);
    }

    //Create the notification that’ll be posted to Channel One//
    public NotificationCompat.Builder getNotification1(String title, String body) {
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ONE_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_warning_black_24dp)
                .setAutoCancel(true);
    }

    //Create the notification that’ll be posted to Channel Two//
    public NotificationCompat.Builder getNotification2(String title, String body) {
        return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_TWO_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_not_chat)
                .setAutoCancel(true);
    }


    public void notify(int id, NotificationCompat.Builder notification) {
        getManager().notify(id, notification.build());
    }

    //Send your notifications to the NotificationManager system service//
    private NotificationManager getManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    public void openNotificationSettings() {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        } else {
            Log.e(LOG_TAG, "can't open notification channel settings");
        }
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        startActivity(intent);
    }

    public void openNotificationSettings(String channel) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        } else {
            Log.e(LOG_TAG, "can't open notification channel settings");
        }
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel);

        startActivity(intent);
    }
}