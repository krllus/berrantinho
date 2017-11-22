package com.example.joao.myapplication.notification.model;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.support.annotation.NonNull;

import com.example.joao.myapplication.notification.NotificationIntentService;

/**
 * Created by Joao Carlos on 11/20/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */


public class SuplementacaoNotification extends NotificationBase {

    private int notificationID = -1;

    //parcel implementation
    private SuplementacaoNotification(Parcel in) {
        super(in);
        this.notificationID = in.readInt();
    }

    //default constructor
    SuplementacaoNotification(@NonNull String messageTitle, @NonNull String messageContent) {
        super(messageTitle, messageContent);
        this.notificationID = (int) (System.currentTimeMillis() / 1000);
    }

    @Override
    public Intent getIntent(Context context) {
        return null;
    }

    @Override
    public int getID() {
        return notificationID;
    }

    @Override
    public String getSubtitle() {
        return "Reposição de Suplementos";
    }

    @Override
    public String getGroup() {
        return NOTIFICATION_GROUP_SUPLEMENTACAO;
    }

    @Override
    public String getChannelID() {
        return NOTIFICATION_CHANNEL_SUPLEMENTACAO_ID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(notificationID);
    }

    public static final Creator<SuplementacaoNotification> CREATOR = new Creator<SuplementacaoNotification>() {
        @Override
        public SuplementacaoNotification createFromParcel(Parcel source) {
            return new SuplementacaoNotification(source);
        }

        @Override
        public SuplementacaoNotification[] newArray(int size) {
            return new SuplementacaoNotification[size];
        }
    };

}
