package com.example.joao.berrantinho;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.joao.berrantinho.dialog.SimpleDialog;
import com.example.joao.berrantinho.features.bolinha.BolinhaActivity;
import com.example.joao.berrantinho.features.databinding.NumbersActivity;
import com.example.joao.berrantinho.features.ingredients.IngredientsActivity;
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

            case R.id.launch_bolinha:
                intent = new Intent(this, BolinhaActivity.class);
                startActivity(intent);
                break;

            case R.id.launch_dialog:
                showSimpleDialog();
                break;

            case R.id.launch_data_binding:
                intent = new Intent(this, NumbersActivity.class);
                startActivity(intent);
                break;

            case R.id.launch_ingredients:
                intent = new Intent(this, IngredientsActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void showSimpleDialog() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment previous = getFragmentManager().findFragmentByTag(SimpleDialog.DIALOG_TAG);
        if (previous != null) {
            fragmentTransaction.remove(previous);
        }
        fragmentTransaction.addToBackStack(null);
        SimpleDialog dialog = new SimpleDialog();
        dialog.show(fragmentTransaction, SimpleDialog.DIALOG_TAG);
    }


}
