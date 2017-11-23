package com.example.joao.berrantinho.notification;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.notification.model.NotificationBase;

import java.util.ArrayList;

public class NotificationIntentService extends IntentService {
    private static final String LOG_TAG = NotificationIntentService.class.getSimpleName();

    private static final String ACTION_START_NOTIFICATION = "notification.action.START";

    private static final String EXTRA_NOTIFICATION = "notification.extra.NOTIFICATION";
    private static final String EXTRA_NOTIFICATION_ID = "notification.extra.NOTIFICATION_ID";
    private static final String EXTRA_NOTIFICATION_CHANNEL_ID = "notification.extra.NOTIFICATION_CHANNEL_ID";

    private NotificationManagerCompat notificationManagerCompat;
    private NotificationManager notificationManager;

    public NotificationIntentService() {
        super(NotificationIntentService.class.getSimpleName());
    }

    /**
     * If the service is already performing a task this action will be queued.
     */
    public static void startNotification(Context context, NotificationBase notification, int notificationID, String channel) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_START_NOTIFICATION);
        intent.putExtra(EXTRA_NOTIFICATION, notification);
        intent.putExtra(EXTRA_NOTIFICATION_ID, notificationID);
        intent.putExtra(EXTRA_NOTIFICATION_CHANNEL_ID, channel);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        createChannels();

        assert intent != null;
        final String action = intent.getAction();

        final int notificationID;
        final NotificationBase notification;
        final String notificationChannelID;

        assert action != null;
        switch (action) {
            case ACTION_START_NOTIFICATION:
                notification = intent.getParcelableExtra(EXTRA_NOTIFICATION);
                notificationID = intent.getIntExtra(EXTRA_NOTIFICATION_ID, -1);
                notificationChannelID = intent.getStringExtra(EXTRA_NOTIFICATION_CHANNEL_ID);
                handleStartNotification(notification, notificationID, notificationChannelID);
                break;
        }
    }

    private void handleStartNotification(NotificationBase notificationBase, int notificationID, String channel) {
        NotificationManagerCompat notificationManager = getNotificationManagerCompatService();

        //create the default notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channel)
                .setContentTitle(notificationBase.getTitle())
                .setContentText(notificationBase.getContent())
                .setGroup(notificationBase.getGroup())
                .setSmallIcon(R.drawable.ic_not_chat)
                .setAutoCancel(true);
        //.setContentIntent(resolvePendingIntentForNotification(notificationBase))


        Notification builtNotification = notificationBuilder.build();

        // deliver the standard notification
        notificationManager.notify(notificationID, builtNotification);

        //stack notifications in case of need
        stackNotificationsIfNeeded(notificationBase);
    }

    private PendingIntent resolvePendingIntentForNotification(NotificationBase notificationBase) {
        Intent resultIntent = notificationBase.getIntent(this);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);

        return PendingIntent.getActivity(
                this,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }

    //Send your notifications to the NotificationManager system service//
    private NotificationManagerCompat getNotificationManagerCompatService() {
        if (notificationManagerCompat == null) {
            notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        }
        return notificationManagerCompat;
    }

    protected NotificationManager getNotificationManagerService() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    void createChannels() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }

        NotificationChannel notificationChannel = new NotificationChannel(
                NotificationBase.NOTIFICATION_CHANNEL_SUPLEMENTACAO_ID,
                NotificationBase.NOTIFICATION_CHANNEL_SUPLEMENTACAO_NAME,
                NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setShowBadge(true);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        getNotificationManagerService().createNotificationChannel(notificationChannel);
    }

    private void stackNotificationsIfNeeded(NotificationBase notification) {
        // se versao android for diferente de 23, nao fazer stack de notifications...
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

        ArrayList<StatusBarNotification> groupedNotifications = new ArrayList<>();

        // step through all the active StatusBarNotifications and
        for (StatusBarNotification sbn : getNotificationManagerService().getActiveNotifications()) {
            // add any previously sent notifications with a group that matches our RemoteNotification
            // and exclude any previously sent stack notifications
            if (notification.getGroup() != null
                    && notification.getGroup().equals(sbn.getNotification().getGroup())
                    && sbn.getId() != NotificationBase.TYPE_STACK) {
                groupedNotifications.add(sbn);
            }
        }

        // since we assume the most recent notification was delivered just prior to calling this method,
        // we check that previous notifications in the group include at least 2 notifications
        if (groupedNotifications.size() > 1) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(
                    this,
                    notification.getChannelID());

            // use convenience methods on our RemoteNotification wrapper to create a title
            builder.setContentTitle(String.format("%s: %s", notification.getTitle(), notification.getSubtitle()))
                    .setContentText(String.format("%d novas mensagens", groupedNotifications.size()));

            NotificationCompat.InboxStyle inbox = new NotificationCompat.InboxStyle();
            {
                for (StatusBarNotification statusBarNotification : groupedNotifications) {
                    String stackNotificationLine = (String) statusBarNotification.getNotification().extras.get(NotificationCompat.EXTRA_TEXT);
                    if (stackNotificationLine != null)
                        inbox.addLine(stackNotificationLine);
                }

                inbox.setSummaryText(String.format("%d novas atividades", groupedNotifications.size()));
            }
            builder.setStyle(inbox);

            builder.setGroup(notification.getGroup())
                    .setGroupSummary(true);

            builder.setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            builder.setSmallIcon(R.drawable.ic_not_chat);

            // create a unique PendingIntent using an integer request code.
            final int requestCode = (int) System.currentTimeMillis() / 1000;
            //builder.setContentIntent(PendingIntent.getActivity(this, requestCode, notification.getIntent(this), PendingIntent.FLAG_ONE_SHOT));

            Notification stackNotification = builder.build();
            stackNotification.defaults = Notification.DEFAULT_ALL;

            getNotificationManagerService().notify(
                    NotificationBase.TYPE_STACK,
                    stackNotification);
        }


    }

    /*
    private NotificationCompat.Builder getSingleNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_SUPLEMENTACAO_ID);
        return builder.setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_not_chat)
                .setGroupSummary(true)
                .setGroup(GROUP_SUPLEMENTACAO);
    }

    private NotificationCompat.Builder getGroupSummaryNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_SUPLEMENTACAO_ID);
        return builder.setContentTitle("Dummy content title")
                .setContentText("Dummy content text")
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Line 1")
                        .addLine("Line 2")
                        .setSummaryText("Inbox summary text")
                        .setBigContentTitle("Big content title"))
                .setNumber(2)
                .setSmallIcon(R.drawable.ic_not_chat)
                .setGroupSummary(true)
                .setGroup(GROUP_SUPLEMENTACAO);

        //Notification notification = builder.build();
        //notificationManager.notify(notificationId, notification);
    }
    */
}