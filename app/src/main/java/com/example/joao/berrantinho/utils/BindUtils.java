package com.example.joao.berrantinho.utils;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.widget.TextView;

/**
 * Created by Joao Carlos on 12/11/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 * https://github.com/danponce/bindtest/blob/master/app/src/main/java/com/example/dan/bindtest/BindUtils.java
 */

public class BindUtils {

  @BindingAdapter("android:text")
  public static void setDouble(TextView view, Double val) {
    if (val != null) {
      String currentValue = view.getText().toString();
      if (currentValue.length() != 0) {
        try {
          double oldVal = Double.parseDouble(currentValue);
          if (oldVal == val) {
            return;
          }
        } catch (NumberFormatException e) {
          // that's ok, we can just set the value.
        }
      }
      view.setText(val.toString());
    }
  }

  @InverseBindingAdapter(attribute = "android:text")
  public static Double getDouble(TextView view) {
    try {
      return Double.parseDouble(view.getText().toString());
    } catch (NumberFormatException e) {
      return null;
    }
  }

  @BindingAdapter("android:text")
  public static void setInteger(TextView view, Integer val) {
    if (val != null) {
      String currentValue = view.getText().toString();
      if (currentValue.length() != 0) {
        try {
          int oldVal = Integer.parseInt(currentValue);
          if (oldVal == val) {
            return;
          }
        } catch (NumberFormatException e) {
          // that's ok, we can just set the value.
        }
      }
      view.setText(val.toString());
    }
  }

  @InverseBindingAdapter(attribute = "android:text")
  public static Integer getInteger(TextView view) {
    try {
      return Integer.parseInt(view.getText().toString());
    } catch (NumberFormatException e) {
      return null;
    }
  }
}