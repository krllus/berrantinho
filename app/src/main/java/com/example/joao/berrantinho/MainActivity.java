package com.example.joao.berrantinho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.joao.berrantinho.dialog.SimpleDialog;
import com.example.joao.berrantinho.features.bolinha.BolinhaActivity;
import com.example.joao.berrantinho.features.databinding.NumbersActivity;
import com.example.joao.berrantinho.features.file.FilesActivity;
import com.example.joao.berrantinho.features.flower.SelectableFlowerActivity;
import com.example.joao.berrantinho.features.ingredients.IngredientsActivity;
import com.example.joao.berrantinho.features.notification.NotificationActivity;
import com.example.joao.berrantinho.features.piechart.PieChartTest;
import com.example.joao.berrantinho.features.seekbar.SeekBarActivity;
import com.example.joao.berrantinho.features.suplementacao.SuplementacaoActivity;
import com.example.joao.berrantinho.features.text.TextSampleActivity;
import com.example.joao.berrantinho.features.toolbar.ToolbarActivity;
import com.example.joao.berrantinho.textwatcher.ValorMonetario;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_number);
        editText.addTextChangedListener(new ValorMonetario(editText));
    }

    @Override
    public void onClick(View view) {

        Intent intent;
        switch (view.getId()) {

            case R.id.show_number:
                NumberFormat format = NumberFormat.getInstance();
                Number number;
                try {
                    number = format.parse(editText.getText().toString());
                    double d = number.doubleValue();
                    double dobro = d * 2;
                    Toast.makeText(this, String.format(Locale.getDefault(), "%f <-> %f ", d, dobro), Toast.LENGTH_LONG).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.launch_notification:
                intent = new Intent(this, NotificationActivity.class);
                startActivity(intent);
                break;

            case R.id.launch_suplementacao:
                intent = new Intent(this, SuplementacaoActivity.class);
                startActivity(intent);
                break;

            case R.id.launch_toolbar:
                intent = new Intent(this, ToolbarActivity.class);
                startActivity(intent);
                break;

            case R.id.launch_bolinha:
                intent = new Intent(this, BolinhaActivity.class);
                startActivity(intent);
                break;

            case R.id.launch_dialog:
                showSimpleDialog();
                break;

            case R.id.launch_data_binding:
                intent = new Intent(this, NumbersActivity.class);
                startActivity(intent);
                break;

            case R.id.launch_ingredients:
                intent = new Intent(this, IngredientsActivity.class);
                startActivity(intent);
                break;

            case R.id.launch_flower:
                intent = new Intent(this, SelectableFlowerActivity.class);
                startActivity(intent);
                break;

            case R.id.launch_dialog_2:
                AlertDialog dialog = new AlertDialog.Builder(this, R.style.AlertDialogTheme)
                        .setTitle("Such Title")
                        .setMessage("such message, much wow, with long text... 3,6,9, dam she's fine. to the windownwwww to the wall, to that wall")
                        .setNegativeButton("Cancelar", null)
                        .setPositiveButton("Cotinuar", null)
                        .create();

                dialog.show();

            case R.id.launch_dialog_3:
                intent = new Intent(this, TextSampleActivity.class);
                startActivity(intent);
                break;

            case R.id.launch_seekbar_activity:
                intent = new Intent(this, SeekBarActivity.class);
                startActivity(intent);
                break;

            case R.id.launch_pie_chart:
                intent = new Intent(this, PieChartTest.class);
                startActivity(intent);
                break;

            case R.id.launch_create_file:
                intent = new Intent(this, FilesActivity.class);
                startActivity(intent);
                break;

        }
    }

    private void showSimpleDialog() {
        SimpleDialog dialog = new SimpleDialog();
        dialog.show(getSupportFragmentManager(), SimpleDialog.DIALOG_TAG);
    }


}
