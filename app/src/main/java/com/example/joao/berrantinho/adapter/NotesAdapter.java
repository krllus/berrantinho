package com.example.joao.berrantinho.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.model.Note;
import com.example.joao.berrantinho.viewholder.NoteViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by João Carlos on 5/11/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
public class NotesAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private static final int NO_POSITION = -1;
    private List<Note> elements;

    public NotesAdapter() {
        this.elements = new ArrayList<>();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_note, parent, false);

        final NoteViewHolder holder = new NoteViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if (pos != NO_POSITION) {
                    holder.checkBox.toggle();
                }
            }
        });

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int pos = holder.getAdapterPosition();
                if (pos != NO_POSITION) {
                    elements.get(pos).setSelected(isChecked);
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note element = elements.get(position);
        holder.bindView(element);
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public List<Note> getElements() {
        return elements;
    }

    public void updateElements(List<Note> elements) {
        Collections.sort(elements, new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                return o2.getFile().getName().compareTo(o1.getFile().getName());
            }
        });
        this.elements.clear();
        this.elements.addAll(elements);
        notifyDataSetChanged();
    }
}
