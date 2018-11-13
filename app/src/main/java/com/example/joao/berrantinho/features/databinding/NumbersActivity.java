package com.example.joao.berrantinho.features.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.databinding.SampleItemBinding;
import com.example.joao.berrantinho.model.SampleModel;
import java.util.Locale;

/**
 * Created by Joao Carlos on 12/11/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class NumbersActivity extends AppCompatActivity {
  SampleModel data;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SampleItemBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_numbers);
    data = new SampleModel("sample");
    binding.setData(data);
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  public void click(View v) {
    Log.d("LOG", String.format(Locale.getDefault(), "%s %f %f",
        data.getSampleText(), data.getNumberOne(), data.getNumberTwo()));
  }
}
