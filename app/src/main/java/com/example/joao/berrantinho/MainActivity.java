package com.example.joao.berrantinho;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joao.berrantinho.dialog.SimpleDialog;
import com.example.joao.berrantinho.features.bolinha.BolinhaActivity;
import com.example.joao.berrantinho.features.databinding.NumbersActivity;
import com.example.joao.berrantinho.features.flower.FlowerActivity;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
                intent = new Intent(this, FlowerActivity.class);
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

        }
    }

    private void showSimpleDialog() {
        SimpleDialog dialog = new SimpleDialog();
        dialog.show(getSupportFragmentManager(), SimpleDialog.DIALOG_TAG);
    }

    private class MyCustomAdapter extends ArrayAdapter<String> {

        private List<String> elements;
        private int resource;

        public MyCustomAdapter(@NonNull Context context, int resource) {
            super(context, resource);
            elements = new ArrayList<>();
            this.resource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null)
                convertView = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
            String element = elements.get(position);
            ((TextView) convertView).setText(element);
            convertView.setTag(position);
            return convertView;
        }

        @Nullable
        @Override
        public String getItem(int position) {
            return elements.get(position);
        }

        @Override
        public int getCount() {
            return elements.size();
        }

        public void setElements(List<String> elements) {
            this.elements.clear();
            this.elements.addAll(elements);
            notifyDataSetChanged();
        }
    }


}
