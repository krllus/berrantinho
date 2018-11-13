package com.example.joao.berrantinho.features.ingredients;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.databinding.ActivityIngredientsBinding;
import com.example.joao.berrantinho.databinding.RowIngredienteBinding;
import com.example.joao.berrantinho.model.Ingrediente;

/**
 * Created by Joao Carlos on 12/12/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class IngredientsActivity extends AppCompatActivity {

  ActivityIngredientsBinding binding;
  IngredientViewModel model;
  LinearLayout ingredientContainer;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil
        .setContentView(this, R.layout.activity_ingredients);

    ingredientContainer = findViewById(R.id.ingredientContainer);

    model = new IngredientViewModel();

    binding.setObj(model);
    binding.setPresenter(new Presenter() {
      @Override
      public void onAddIngredient() {

        RowIngredienteBinding b =
            DataBindingUtil.inflate(getLayoutInflater(), R.layout.row_ingrediente,
                ingredientContainer, false);

        String nome = "Elemento " + model.getBindings().size();
        Double value = Math.random() * 5 + 1;
        Ingrediente ingrediente = Ingrediente.createDumbElement(nome, value);
        b.setObj(ingrediente);

        model.addBindingItem(b);

        ingredientContainer.addView(b.getRoot());

        Log.d("add", "child count: " + ingredientContainer.getChildCount());
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
  }
}
