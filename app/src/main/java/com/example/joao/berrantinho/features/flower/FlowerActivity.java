package com.example.joao.berrantinho.features.flower;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.adapter.SimpleAdapter;
import com.example.joao.berrantinho.model.Simple;

import java.util.ArrayList;
import java.util.List;


public class FlowerActivity extends AppCompatActivity {

    private SimpleAdapter adapter;

    private List<Simple> elements;

    private String names[] = {
            "Monk's Oartormow",
            "Eldoth Nut",
            "Eblurst",
            "Poison Okule",
            "Black Berry",
            "Snake Eye",
            "Witch's Root",
            "White Burm",
            "Yellow Pig Claw",
            "Uchea Mint"
    };

    private String contents[] = {
            "What if the adaptable injury ate the click?",
            "The united chip stuffs into the babyish sad.",
            "Did the interesting progress really argue the visual?",
            "What if the chief sign ate the schedule?",
            "Is the brush respect better than the raise?",
            "It was then the inexperienced topic met the open period."
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        elements = new ArrayList<>();

        adapter = new SimpleAdapter();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Simple s = generateRandomElement();
                elements.add(0,s);
                adapter.updateElements(elements);
            }
        });
    }

    private Simple generateRandomElement() {
        int posNames = (int) (Math.random() * names.length);
        int posContent = (int) (Math.random() * contents.length);
        String name = names[posNames];
        String content = contents[posContent];

        return new Simple(name, content);
    }

}
