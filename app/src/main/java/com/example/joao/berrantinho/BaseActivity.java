package com.example.joao.berrantinho;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by João Carlos Ferreira Marques on 11/23/17.
 * joaocarlusferrera at gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setUpToolbar();

    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(getActionBarTitle());

            //inflate toolbar
            //inflate toolbar content
        }
    }


    public abstract CharSequence getActionBarTitle();

    public int getCustomToolbarContent() {
        return -1;
    }
    //setup title toolbar
    //init content
    //show/hide progressbar
    //hide/show fragment_container
}
