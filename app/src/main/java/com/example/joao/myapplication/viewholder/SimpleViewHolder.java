package com.example.joao.myapplication.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.joao.myapplication.R;

/**
 * Created by Joao Carlos on 11/22/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */


public class SimpleViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView content;

    public SimpleViewHolder(View view) {
        super(view);
        title = view.findViewById(R.id.item_title);
        content = view.findViewById(R.id.item_content);
    }
}
