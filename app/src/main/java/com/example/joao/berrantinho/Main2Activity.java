package com.example.joao.berrantinho;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.joao.berrantinho.adapter.SimpleAdapter;
import com.example.joao.berrantinho.model.Simple;
import com.example.joao.berrantinho.model.SimpleEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    SimpleAdapter adapter;

    SimpleEnum simpleEnum;

    TextView filterVazio;
    TextView filterRepor;
    TextView filterComProduto;
    TextView filterTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        simpleEnum = SimpleEnum.A;

        setUpToolbar();
        setUpViews();
    }

    private void setUpViews() {

        filterVazio = findViewById(R.id.filter_vazio);
        filterRepor = findViewById(R.id.filter_repor);
        filterComProduto = findViewById(R.id.filter_com_produto);
        filterTodos = findViewById(R.id.filter_all);

        filterVazio.setOnClickListener(this);
        filterRepor.setOnClickListener(this);
        filterComProduto.setOnClickListener(this);
        filterTodos.setOnClickListener(this);

        recyclerView = findViewById(R.id.recycler_view);

        adapter = new SimpleAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateElements(generateDumbElements());
        applyFilter();
    }

    public List<Simple> generateDumbElements() {
        List<Simple> list = new ArrayList<>();
        Simple simple;

        String content = getResources().getString(R.string.dumb_text);

        for (int i = 1; i < 10; i++) {
            String title = String.format(Locale.getDefault(), "Title %02d", i);
            simple = new Simple(title, content);
            list.add(simple);
        }
        return list;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.filter_vazio:
                simpleEnum = SimpleEnum.A;
                applyFilter();
                break;

            case R.id.filter_repor:
                simpleEnum = SimpleEnum.B;
                applyFilter();
                break;

            case R.id.filter_com_produto:
                simpleEnum = SimpleEnum.C;
                applyFilter();
                break;

            case R.id.filter_all:
                simpleEnum = SimpleEnum.D;
                applyFilter();
                break;
        }
    }

    private void applyFilter() {
        if (simpleEnum == SimpleEnum.A) {
            filterVazio.setTypeface(null, Typeface.BOLD_ITALIC);
            filterVazio.setSelected(true);
        } else {
            filterVazio.setTypeface(null, Typeface.NORMAL);
            filterVazio.setSelected(false);
        }

        if (simpleEnum == SimpleEnum.B) {
            filterRepor.setTypeface(null, Typeface.BOLD_ITALIC);
            filterRepor.setSelected(true);
        } else {
            filterRepor.setTypeface(null, Typeface.NORMAL);
            filterRepor.setSelected(false);
        }

        if (simpleEnum == SimpleEnum.C) {
            filterComProduto.setTypeface(null, Typeface.BOLD_ITALIC);
            filterComProduto.setSelected(true);
        } else {
            filterComProduto.setTypeface(null, Typeface.NORMAL);
            filterComProduto.setSelected(false);
        }

        if (simpleEnum == SimpleEnum.D) {
            filterTodos.setTypeface(null, Typeface.BOLD_ITALIC);
            filterTodos.setSelected(true);
        } else {
            filterTodos.setTypeface(null, Typeface.NORMAL);
            filterTodos.setSelected(false);
        }
    }
}
