package com.example.joao.berrantinho.textwatcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import java.text.NumberFormat;

/**
 * Created by João Carlos on 1/30/18.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class ValorMonetario implements TextWatcher {

  private String current = "";

  private EditText editText;

  public ValorMonetario(EditText editText) {
    this.editText = editText;
  }

  @Override
  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

  }

  @Override
  public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

  }

  @Override
  public void afterTextChanged(Editable editable) {
    if (!editable.toString().equals(current)) {
      editText.removeTextChangedListener(this);

      String replaceable =
          String.format("[%s,.\\s]", NumberFormat.getCurrencyInstance().getCurrency().getSymbol());
      String cleanString = editable.toString().replaceAll(replaceable, "");

      double parsed;
      try {
        parsed = Double.parseDouble(cleanString);
      } catch (NumberFormatException e) {
        parsed = 0.00;
      }
      String formatted = NumberFormat.getCurrencyInstance().format((parsed / 100));

      //clear currency symbol
      replaceable =
          String.format("[%s]", NumberFormat.getCurrencyInstance().getCurrency().getSymbol());
      formatted = formatted.replaceAll(replaceable, "");

      current = formatted;
      editText.setText(formatted);
      editText.setSelection(formatted.length());
      editText.addTextChangedListener(this);
    }
  }
}
