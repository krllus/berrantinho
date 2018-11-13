package com.example.joao.berrantinho.features.flower;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.RecyclerItemClickListener;
import com.example.joao.berrantinho.adapter.MultiItemSeletorAdapter;
import com.example.joao.berrantinho.model.Simple;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Created by João Carlos on 5/15/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 * https://learnpainless.com/android/recyclerview/multiple-selection-recyclerview-android
 */

public class SelectableFlowerActivity
    extends AppCompatActivity
    implements ActionMode.Callback {

  private static final String KEY_LETTERS = "key_letters";
  private MultiItemSeletorAdapter adapter;
  private ActionMode actionMode;
  private boolean isMultiSelect = false;

  private HashSet<String> selectedIDs;
  private List<Simple> elements;

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
    adapter = new MultiItemSeletorAdapter();
    random = new Random();

    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);

    recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView,
        new RecyclerItemClickListener.OnItemClickListener() {
          @Override
          public void onItemClick(View view, int position) {
            if (isMultiSelect) {
              multiSelect(position);
            }
          }

          @Override
          public void onItemLongClick(View view, int position) {
            if (!isMultiSelect) {
              selectedIDs = new HashSet<>();
              isMultiSelect = true;

              if (actionMode == null) {
                actionMode = startSupportActionMode(SelectableFlowerActivity.this);
              }
            }

            multiSelect(position);
          }
        }));

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

  private void multiSelect(int position) {
    Simple item = adapter.getItem(position);
    if (item != null && actionMode != null) {
      if (selectedIDs.contains(item.getId())) {
        selectedIDs.remove(item.getId());
      } else {
        selectedIDs.add(item.getId());
      }

      if (selectedIDs.size() > 0) {
        actionMode.setTitle(String.valueOf(selectedIDs.size()));
      } else {
        actionMode.setTitle("");
        actionMode.finish();
      }

      adapter.setSelectedIds(selectedIDs);
    }
  }

  @Override
  public boolean onCreateActionMode(ActionMode mode, Menu menu) {
    MenuInflater inflater = mode.getMenuInflater();
    inflater.inflate(R.menu.menu_select, menu);
    return true;
  }

  @Override
  public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
    return false;
  }

  @Override
  public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_share:
        //just to show selected items.
        StringBuilder stringBuilder = new StringBuilder();
        for (Simple data : adapter.getElements()) {
          if (selectedIDs.contains(data.getId())) {
            stringBuilder.append("\n").append(data.getContent());
          }
        }
        Toast.makeText(this, "Selected items are :" + stringBuilder.toString(), Toast.LENGTH_SHORT)
            .show();
        return true;
    }
    return false;
  }

  @Override
  public void onDestroyActionMode(ActionMode mode) {
    actionMode = null;
    isMultiSelect = false;
    selectedIDs = new HashSet<>();
    adapter.setSelectedIds(selectedIDs);
  }
}
