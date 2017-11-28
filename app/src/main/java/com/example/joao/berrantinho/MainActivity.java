package com.example.joao.berrantinho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.joao.berrantinho.features.notification.NotificationActivity;
import com.example.joao.berrantinho.features.suplementacao.SuplementacaoActivity;
import com.example.joao.berrantinho.features.toolbar.ToolbarActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {

        Intent intent;
        switch (view.getId()) {
            case R.id.launch_notification:
                intent = new Intent(this, NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.launch_suplementacao:
                intent = new Intent(this, SuplementacaoActivity.class);
                startActivity(intent);
                break;

            case R.id.launch_toolbar:
                intent = new Intent(this, ToolbarActivity.class);
                startActivity(intent);
                break;
        }
    }


}
