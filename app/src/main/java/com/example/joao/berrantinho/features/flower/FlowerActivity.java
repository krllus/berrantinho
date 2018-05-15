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
import java.util.Random;


public class FlowerActivity extends AppCompatActivity {

    private static final String KEY_LETTERS = "key_letters";
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
    private Random random;
    private ArrayList<Character> usedLetters;
    private String alphabet = "0123456789abcdefghABCDEFGH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usedLetters = new ArrayList<>();
        elements = new ArrayList<>();
        adapter = new SimpleAdapter();
        random = new Random();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Simple s = generateRandomElement();
                Character character = alphabet.charAt(random.nextInt(alphabet.length()));
                usedLetters.add(character);

                Simple s = new Simple("Letter", character.toString());
                elements.add(0, s);
                adapter.updateElements(elements);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String usedLettersStringRepresentation = getStringRepresentation(usedLetters);
        outState.putString(KEY_LETTERS, usedLettersStringRepresentation);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String usedLettersStringRepresentation = savedInstanceState.getString(KEY_LETTERS);
        if (usedLettersStringRepresentation == null) return;
        for (int i = 0; i < usedLettersStringRepresentation.length(); i++) {
            Character character = usedLettersStringRepresentation.charAt(i);
            usedLetters.add(character);
            elements.add(0, new Simple("Letter", character.toString()));
        }

        adapter.updateElements(elements);
    }

    private String getStringRepresentation(ArrayList<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());
        for (Character ch : list) {
            builder.append(ch);
        }
        return builder.toString();
    }

}
