package com.example.joao.berrantinho.features.bolinha;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.adapter.BolinhaAdapter;
import com.example.joao.berrantinho.model.Bolinha;

/**
 * Created by Joao Carlos on 11/29/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */


public class BolinhaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BolinhaAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolinha);

        recyclerView = findViewById(R.id.recycler_view);

        adapter = new BolinhaAdapter(getFragmentManager());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateElements(Bolinha.createDummyBolinhaItens());
    }
}
