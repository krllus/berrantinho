package com.example.joao.berrantinho.viewholder;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.joao.berrantinho.R;
import com.example.joao.berrantinho.model.Simple;
import java.util.HashSet;

/**
 * Created by Joao Carlos on 11/22/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class SimpleViewHolder extends RecyclerView.ViewHolder {
  public TextView title;
  public TextView content;
  private Context context;

  public SimpleViewHolder(View view) {
    super(view);
    context = view.getContext();

    title = view.findViewById(R.id.item_title);
    content = view.findViewById(R.id.item_content);
  }

  public void bindView(Simple element, HashSet<String> selectedIds) {
    title.setText(element.getTitle());
    content.setText(element.getContent());

    if (selectedIds.contains(element.getId())) {
      ((CardView) itemView).setForeground(
          new ColorDrawable(ContextCompat.getColor(context, R.color.colorControlActivated)));
    } else {
      ((CardView) itemView).setForeground(
          new ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent)));
    }
  }
}
