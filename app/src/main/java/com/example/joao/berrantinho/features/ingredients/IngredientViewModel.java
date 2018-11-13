package com.example.joao.berrantinho.features.ingredients;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import com.example.joao.berrantinho.BR;
import com.example.joao.berrantinho.databinding.RowIngredienteBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Joao Carlos on 12/12/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class IngredientViewModel implements Observable {
  private PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();
  private List<RowIngredienteBinding> bindings;

  IngredientViewModel() {
    this.bindings = new ArrayList<>();
  }

  @Bindable
  public List<RowIngredienteBinding> getBindings() {
    return bindings;
  }

  public void addBindingItem(RowIngredienteBinding bindings) {
    this.bindings.add(bindings);
    propertyChangeRegistry.notifyChange(this, BR.bindings);
  }

  @Bindable({ "bindings" })
  public String getListPriceSum() {
    Double sum = 0d;
    for (RowIngredienteBinding b : bindings) {
      sum += b.getObj().getQuantidade();
    }

    return String.format(Locale.getDefault(), "%.2f", sum);
  }

  @Override
  public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
    propertyChangeRegistry.add(onPropertyChangedCallback);
  }

  @Override
  public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
    propertyChangeRegistry.remove(onPropertyChangedCallback);
  }
}
