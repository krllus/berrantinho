package com.example.joao.berrantinho.notification.model;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.example.joao.berrantinho.notification.NotificationIntentService;

/**
 * Created by Joao Carlos on 11/20/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public abstract class NotificationBase implements Parcelable {

  public static final int NOTIFICATION_ID_SUPLEMENTACAO = 1;

  public static final String NOTIFICATION_CHANNEL_SUPLEMENTACAO_ID = "SUPLEMENTACAO";
  public static final String NOTIFICATION_CHANNEL_SUPLEMENTACAO_NAME = "Suplementação";

  public static final String NOTIFICATION_GROUP_SUPLEMENTACAO = "Grupo SuplementacaoActivity";

  public static final int TYPE_STACK = -1000;

  private String notificationTitle;
  private String notificationContent;

  NotificationBase(@NonNull String messageTitle, @NonNull String messageContent) {
    this.notificationTitle = messageTitle;
    this.notificationContent = messageContent;
  }

  NotificationBase(Parcel in) {
    this.notificationTitle = in.readString();
    this.notificationContent = in.readString();
  }

  public void sendNotificationEvent(Context context) {
    NotificationIntentService.startNotification(context, this, getID(), getChannelID());
  }

  public String getTitle() {
    return notificationTitle;
  }

  public String getContent() {
    return notificationContent;
  }

  public abstract Intent getIntent(Context context);

  public abstract int getID();

  public abstract String getSubtitle();

  public abstract String getGroup();

  public abstract String getChannelID();

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.notificationTitle);
    dest.writeString(this.notificationContent);
  }
}
