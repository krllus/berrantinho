package com.example.joao.berrantinho.features.ingredients;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.adapter.IngredientesAdapter;
import com.example.joao.berrantinho.model.Ingrediente;

import java.util.List;

/**
 * Created by Joao Carlos on 12/12/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */


public class IngredientsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    IngredientesAdapter adapter;

    List<Ingrediente> elementos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        recyclerView = findViewById(R.id.recyclerView);

        adapter = new IngredientesAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.elementos = Ingrediente.createDumbElements(4);
        adapter.updateElements(elementos);
    }
}
