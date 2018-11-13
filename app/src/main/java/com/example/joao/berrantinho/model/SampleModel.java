package com.example.joao.berrantinho.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import com.example.joao.berrantinho.BR;
import java.util.Locale;

/**
 * Created by Joao Carlos on 12/11/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class SampleModel implements Observable {
  private PropertyChangeRegistry pcr = new PropertyChangeRegistry();

  private String sampleText;
  private Double numberOne;
  private Double numberTwo;

  public SampleModel(String text) {
    this.sampleText = text;
    this.numberOne = 0d;
    this.numberTwo = 0d;
  }

  @Bindable
  public String getSampleText() {
    return sampleText;
  }

  public void setSampleText(String sampleText) {
    this.sampleText = sampleText;
    pcr.notifyChange(this, BR.sampleText);
  }

  @Bindable
  public Double getNumberOne() {
    return numberOne;
  }

  public void setNumberOne(Double numberOne) {
    this.numberOne = numberOne;
    pcr.notifyChange(this, BR.numberOne);
  }

  @Bindable
  public Double getNumberTwo() {
    return numberTwo;
  }

  public void setNumberTwo(Double numberTwo) {
    this.numberTwo = numberTwo;
    pcr.notifyChange(this, BR.numberTwo);
  }

  @Bindable({ "numberOne", "numberTwo" })
  public String getNumbersSum() {
    Double s = numberOne + numberTwo;
    return String.format(Locale.getDefault(), "%.2f", s);
  }

  @Override
  public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
    pcr.add(callback);
  }

  @Override
  public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
    pcr.remove(callback);
  }

  //    @BindingAdapter({"android:text"})
  //    public static void setText(EditText editText, Double value) {
  //        final CharSequence oldText = editText.getText();
  //        if (!haveContentsChanged(value, oldText))
  //            return;
  //        editText.setText(String.format(Locale.getDefault(), "%.2f", value));
  //    }
  //
  //    @InverseBindingAdapter(attribute = "android:text")
  //    public static Double getTextString(EditText editText) {
  //        String txt = editText.getText().toString();
  //        return "".equals(txt) ? 0d : Double.parseDouble(txt.replace(",", "."));
  //    }
  //
  //    private static boolean haveContentsChanged(Double value, CharSequence oldText) {
  //        String oldContent = oldText.toString();
  //        Double oldDouble = "".equals(oldContent) ? 0d : Double.parseDouble(oldContent);
  //        return !oldDouble.equals(value);
  //    }
}
