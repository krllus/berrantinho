package com.example.joao.berrantinho.notification;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by Joao Carlos on 11/20/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class NotificationHelper extends ContextWrapper {

  private static final String LOG_TAG = NotificationHelper.class.getSimpleName();

  public NotificationHelper(Context base) {
    super(base);
  }

  public void openNotificationSettings() {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
      intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());

      startActivity(intent);
    } else {
      Log.e(LOG_TAG, "can't open notifications settings");
    }
  }

  public void openNotificationSettings(String channel) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
      intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
      intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel);

      startActivity(intent);
    } else {
      Log.e(LOG_TAG, "can't open notification channel settings");
    }
  }
}
