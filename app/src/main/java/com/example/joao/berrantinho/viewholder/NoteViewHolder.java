package com.example.joao.berrantinho.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.model.Note;

/**
 * Created by João Carlos on 5/11/18.
 * Biox Pecuária Moderna
 * desenvolvedorberrante@bioxbr.com
 */
public class NoteViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public CheckBox checkBox;

    public NoteViewHolder(View view) {
        super(view);
        name = view.findViewById(R.id.note_name);
        checkBox = view.findViewById(R.id.checkBox);

    }

    public void bindView(Note note) {
        name.setText(note.getName());
    }

}