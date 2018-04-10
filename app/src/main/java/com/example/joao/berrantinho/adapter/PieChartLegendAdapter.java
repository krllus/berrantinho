package com.example.joao.berrantinho.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joao.berrantinho.R;

import java.util.ArrayList;

public class PieChartLegendAdapter extends RecyclerView.Adapter<PieChartLegendAdapter.LegendViewHolder> {

    private ArrayList<String> labels;
    private ArrayList<Integer> colors;

    public PieChartLegendAdapter(ArrayList<String> labels, ArrayList<Integer> colors) {
        //todo check same size?
        this.labels = new ArrayList<>(labels);
        this.colors = new ArrayList<>(colors);
    }

    @NonNull
    @Override
    public LegendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.row_legend, parent, false);

        return new LegendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LegendViewHolder holder, int position) {
        String label = labels.get(position);
        int color = colors.get(position);
        holder.label.setText(label);
        holder.shape.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return labels.size();
    }

    class LegendViewHolder extends RecyclerView.ViewHolder {

        ImageView shape;
        TextView label;

        LegendViewHolder(View itemView) {
            super(itemView);

            shape = itemView.findViewById(R.id.legend_shape);
            label = itemView.findViewById(R.id.legend_label);
        }
    }

}
