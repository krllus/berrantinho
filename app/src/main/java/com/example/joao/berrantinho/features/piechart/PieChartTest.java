package com.example.joao.berrantinho.features.piechart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.adapter.PieChartLegendAdapter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Locale;

public class PieChartTest extends AppCompatActivity {

    private PieChart pieChart;
    private RecyclerView legendRecyclerView;
    private TextView txtHighlightLabel;

    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        pieChart = findViewById(R.id.pie_chart);
        legendRecyclerView = findViewById(R.id.pie_chart_legend);
        txtHighlightLabel = findViewById(R.id.pie_chart_highlight_label);

        setUpPieChart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void setUpPieChart() {
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawEntryLabels(false);
        pieChart.setDrawCenterText(false);
        pieChart.setDrawMarkers(true);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setRotationEnabled(false);
        pieChart.setHighlightPerTapEnabled(true);
        pieChart.setTouchEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setExtraOffsets(0, 24, 0, 0);

        //disable legend
        pieChart.getLegend().setEnabled(false);

        //legend recycler view
        legendRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void loadData() {
        final ArrayList<PieEntry> entries = new ArrayList<>();
        PieEntry entry1 = new PieEntry(1, "Aborto");
        PieEntry entry2 = new PieEntry(22, "Baixa Produção de Leite");
        PieEntry entry3 = new PieEntry(3, "Idade Avançada");
        PieEntry entry4 = new PieEntry(4, "Ineficiencia Reprodutiva");
        PieEntry entry5 = new PieEntry(5, "Enfermidades Reprodutivas");
        PieEntry entry6 = new PieEntry(16, "Mastite");
        PieEntry entry7 = new PieEntry(17, "Má Conformação Ubere");
        PieEntry entry8 = new PieEntry(18, "Má Conformação Reprodutiva");
        PieEntry entry9 = new PieEntry(19, "Seleção Genetica");
        PieEntry entry10 = new PieEntry(10, "Problemas Locomotores");
        PieEntry entry11 = new PieEntry(11, "Temperamento Agressivo");
        PieEntry entry12 = new PieEntry(12, "Traumatismo");
        PieEntry entry13 = new PieEntry(13, "Outras Enfermidades");
        entries.add(entry1);
        entries.add(entry2);
        entries.add(entry3);
        entries.add(entry4);
        entries.add(entry5);
        entries.add(entry6);
        entries.add(entry7);
        entries.add(entry8);
        entries.add(entry9);
        entries.add(entry10);
        entries.add(entry11);
        entries.add(entry12);
        entries.add(entry13);

        //color here
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(this, android.R.color.holo_green_dark));
        colors.add(ContextCompat.getColor(this, android.R.color.holo_green_light));
        colors.add(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        colors.add(ContextCompat.getColor(this, android.R.color.holo_red_light));
        colors.add(ContextCompat.getColor(this, android.R.color.holo_blue_dark));
        colors.add(ContextCompat.getColor(this, android.R.color.holo_blue_bright));
        colors.add(ContextCompat.getColor(this, android.R.color.holo_blue_light));
        colors.add(ContextCompat.getColor(this, android.R.color.holo_purple));
        colors.add(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
        colors.add(ContextCompat.getColor(this, android.R.color.holo_orange_light));
        colors.add(ContextCompat.getColor(this, R.color.amaranth));
        colors.add(ContextCompat.getColor(this, R.color.american_violet));
        colors.add(ContextCompat.getColor(this, R.color.antique_ruby));

        PieDataSet dataSet = new PieDataSet(entries, "Causas Descarte");
        dataSet.setSliceSpace(.5f);
        dataSet.setSelectionShift(4f);
        dataSet.setColors(colors);

        //data set
        final PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(ContextCompat.getColor(this, R.color.white));

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                PieEntry entry = (PieEntry) e;
                float percentage = (entry.getValue() / data.getYValueSum()) * 100;

                String strHighlightLabel = String.format(Locale.getDefault(),
                        "%s\n%2.1f%%",
                        entry.getLabel(),
                        percentage);

                if (toast != null)
                    toast.cancel();

                toast = Toast.makeText(PieChartTest.this, strHighlightLabel, Toast.LENGTH_SHORT);
                toast.show();

                txtHighlightLabel.setText(strHighlightLabel);
            }

            @Override
            public void onNothingSelected() {
                txtHighlightLabel.setText("");
            }
        });
        pieChart.setData(data);
        // undo all highlights
        pieChart.highlightValues(null);
        pieChart.invalidate();

        //legenda
        ArrayList<String> labels = new ArrayList<>();
        for (PieEntry entry : entries) {
            labels.add(entry.getLabel());
        }
        PieChartLegendAdapter legendAdapter = new PieChartLegendAdapter(labels, colors);
        legendRecyclerView.setAdapter(legendAdapter);
    }

}
