package com.example.joao.berrantinho.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.model.Simple;
import com.example.joao.berrantinho.viewholder.SimpleViewHolder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Joao Carlos on 11/22/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */


public class MultiItemSeletorAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

    private List<Simple> elements;
    private HashSet<String> selectedIds;

    public MultiItemSeletorAdapter() {
        this.selectedIds = new HashSet<>();
        this.elements = new ArrayList<>();
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_suplementacao, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        Simple element = elements.get(position);
        holder.bindView(element, selectedIds);
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public Simple getItem(int position) {
        return elements.get(position);
    }

    public List<Simple> getElements() {
        return elements;
    }

    public void updateElements(List<Simple> elements) {
        this.elements.clear();
        this.elements.addAll(elements);
        notifyDataSetChanged();
    }

    public void setSelectedIds(HashSet<String> selectedIds) {
        this.selectedIds.clear();
        this.selectedIds.addAll(selectedIds);
        notifyDataSetChanged();
    }
}
