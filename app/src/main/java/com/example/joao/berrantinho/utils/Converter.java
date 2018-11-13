package com.example.joao.berrantinho.utils;

import android.content.res.Resources;
import android.databinding.InverseMethod;
import android.view.View;
import android.widget.TextView;
import com.example.joao.berrantinho.R;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by Joao Carlos on 12/11/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 * https://github.com/danponce/bindtest/blob/master/app/src/main/java/com/example/dan/bindtest/Converter.java
 */

public class Converter {
  @InverseMethod("toDouble")
  public static String toString(TextView view, Double oldValue, Double value) {
    NumberFormat numberFormat = getNumberFormat(view);
    try {
      // Don't return a different value if the parsed value
      // doesn't change
      String inView = view.getText().toString();
      double parsed = numberFormat.parse(inView).doubleValue();
      if (parsed == value) {
        return view.getText().toString();
      }
    } catch (ParseException e) {
      // Old number was broken
    }
    return numberFormat.format(value);
  }

  public static Double toDouble(TextView view, Double oldValue, String value) {
    NumberFormat numberFormat = getNumberFormat(view);
    try {
      return numberFormat.parse(value).doubleValue();
    } catch (ParseException e) {
      Resources resources = view.getResources();
      String errStr = resources.getString(R.string.badNumber);
      view.setError(errStr);
      return oldValue;
    }
  }

  private static NumberFormat getNumberFormat(View view) {
    Resources resources = view.getResources();
    Locale locale = resources.getConfiguration().locale;
    NumberFormat format = NumberFormat.getNumberInstance(locale);
    if (format instanceof DecimalFormat) {
      DecimalFormat decimalFormat = (DecimalFormat) format;
      decimalFormat.setGroupingUsed(false);
    }
    return format;
  }
}