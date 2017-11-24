package com.example.joao.berrantinho.features.notification;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.joao.berrantinho.BaseFragment;
import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.notification.NotificationHelper;
import com.example.joao.berrantinho.notification.NotificationIntentService;
import com.example.joao.berrantinho.notification.model.NotificationBase;
import com.example.joao.berrantinho.notification.model.SuplementacaoNotification;
import com.example.joao.berrantinho.notification.model.SuplementacaoNotificationBuilder;

/**
 * Created by Joao Carlos on 11/24/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class NotificationFragment extends BaseFragment implements View.OnClickListener {

    private EditText edtChannelOneText;
    private NotificationHelper notificationHelper;
    private Button btnChannelOnePost;
    private Button btnChannelOneSettings;

    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_notification;
    }

    @Override
    public void setUpCustomViews(View rootView) {
        edtChannelOneText = rootView.findViewById(R.id.channel_one_text);
        btnChannelOnePost = rootView.findViewById(R.id.post_to_channel_one);
        btnChannelOneSettings = rootView.findViewById(R.id.channel_one_settings);
    }

    @Override
    public void setUpLayoutListeners() {
        btnChannelOnePost.setOnClickListener(this);
        btnChannelOneSettings.setOnClickListener(this);
    }

    @Override
    public void initDependencies() {
        notificationHelper = new NotificationHelper(getContext());
    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.post_to_channel_one:
                break;
            case R.id.channel_one_settings:
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
                        .setMessageTitle("Title Notification SuplementacaoActivity")
                        .createSuplementacaoNotification();

                NotificationIntentService.startNotification(getContext(),
                        notification,
                        notification.getID(),
                        NotificationBase.NOTIFICATION_CHANNEL_SUPLEMENTACAO_ID);
                break;
        }
    }
}
