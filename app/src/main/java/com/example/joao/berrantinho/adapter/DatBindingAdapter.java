package com.example.joao.berrantinho.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.model.Simple;
import com.example.joao.berrantinho.viewholder.SimpleViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joao Carlos on 11/22/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */


public class DatBindingAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

    private List<Simple> elements;

    public DatBindingAdapter() {
        this.elements = new ArrayList<>();
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_suplementacao, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        Simple element = elements.get(position);
        holder.title.setText(element.getTitle());
        holder.content.setText(element.getContent());
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public void updateElements(List<Simple> elements) {
        this.elements.clear();
        this.elements.addAll(elements);
        notifyDataSetChanged();
    }
}
