package com.example.joao.berrantinho.features.seekbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.joao.berrantinho.R;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.IndicatorSeekBarType;
import com.warkiz.widget.IndicatorType;
import java.util.Locale;

public class SeekBarActivity
    extends AppCompatActivity
    implements View.OnClickListener {

  private static final int GTA_MIN = 1;
  private static final int GTA_START = GTA_MIN;
  private static final int GTA_MAX = 5;

  private static final int ANIMAL_MIN = 1;
  private static final int ANIMAL_START = 45;
  private static final int ANIMAL_MAX = 200;

  private IndicatorSeekBar indicatorSeekBar;
  private int qtdAnimaisEmbarcados;
  private Button btnEmbarcar;

  //gta
  private int qtdGtas;
  private Button btnMoreGta;
  private Button btnLessGta;
  private TextView txtGta;

  //animals
  private int qtdAnimals;
  private Button btnMoreAnimal;
  private Button btnLessAnimal;
  private TextView txtAnimals;

  //reset
  private Button btnReset;
  private Button btnApply;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_seek_bar);
    setUpLayout();
    setUpLayoutListeners();

    init();
  }

  private void setUpLayout() {
    indicatorSeekBar = findViewById(R.id.indicator_seek_bar);

    btnLessGta = findViewById(R.id.gta_button_less);
    btnMoreGta = findViewById(R.id.gta_button_more);
    txtGta = findViewById(R.id.gta_number);

    btnLessAnimal = findViewById(R.id.animal_button_less);
    btnMoreAnimal = findViewById(R.id.animal_button_more);
    txtAnimals = findViewById(R.id.animals_number);

    btnApply = findViewById(R.id.btn_aplicar);
    btnReset = findViewById(R.id.btn_reset);
    btnEmbarcar = findViewById(R.id.btn_embarcar);
  }

  private void setUpLayoutListeners() {
    btnLessGta.setOnClickListener(this);
    btnMoreGta.setOnClickListener(this);
    btnLessAnimal.setOnClickListener(this);
    btnMoreAnimal.setOnClickListener(this);
    btnApply.setOnClickListener(this);
    btnReset.setOnClickListener(this);
    btnEmbarcar.setOnClickListener(this);
  }

  private void init() {
    reset();
  }

  private void reset() {
    qtdAnimals = ANIMAL_START;
    qtdGtas = GTA_START;
    apply();
  }

  private void apply() {
    qtdAnimaisEmbarcados = 0;
    updateQtdAnimals();
    updateQtdGta();
    calculateProgress();
  }

  private void updateQtdGta() {
    txtGta.setText(String.format(Locale.getDefault(),
        "Gtas: %d",
        qtdGtas));
  }

  private void updateQtdAnimals() {
    txtAnimals.setText(String.format(Locale.getDefault(),
        "Animais: %d",
        qtdAnimals));
  }

  private void calculateProgress() {
    //        indicatorSeekBar = indicatorSeekBar.getBuilder()
    //                .setMin(0)
    //                .setMax(qtdAnimals)
    //                .setProgress(qtdAnimaisEmbarcados).build();

    indicatorSeekBar.getBuilder()
        .setSeekBarType(IndicatorSeekBarType.CONTINUOUS_TEXTS_ENDS)
        .setMax(qtdAnimals)
        .setMin(0)
        .setLeftEndText("0")
        .setRightEndText(qtdAnimals + "")
        .setProgress(qtdAnimaisEmbarcados)
        .setBackgroundTrackSize(1)
        .setBackgroundTrackColor(Color.parseColor("#FF0000"))
        .setProgressTrackSize(3)
        .setProgressTrackColor(Color.parseColor("#FF0000"))
        .showIndicator(true)
        .setIndicatorType(IndicatorType.RECTANGLE)
        .setIndicatorColor(Color.parseColor("#FF0000"))
        .apply();
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_aplicar:
        apply();
        break;
      case R.id.btn_reset:
        reset();
        break;
      case R.id.gta_button_less:
        qtdGtas--;
        if (qtdGtas < GTA_MIN) qtdGtas = GTA_MIN;
        updateQtdGta();
        break;
      case R.id.gta_button_more:
        qtdGtas++;
        if (qtdGtas > GTA_MAX) qtdGtas = GTA_MAX;
        updateQtdGta();
        break;
      case R.id.animal_button_less:
        qtdAnimals--;
        if (qtdAnimals < ANIMAL_MIN) qtdAnimals = ANIMAL_MIN;
        updateQtdAnimals();
        break;
      case R.id.animal_button_more:
        qtdAnimals++;
        if (qtdAnimals > ANIMAL_MAX) qtdAnimals = ANIMAL_MAX;
        updateQtdAnimals();
        break;
      case R.id.btn_embarcar:
        qtdAnimaisEmbarcados++;
        if (qtdAnimaisEmbarcados > qtdAnimals) qtdAnimaisEmbarcados = qtdAnimals;
        calculateProgress();
        break;
    }
  }
}
