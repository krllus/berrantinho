package com.example.joao.berrantinho.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import com.example.joao.berrantinho.BR;

/**
 * Created by Joao Carlos on 12/12/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
  private final ViewDataBinding binding;

  public BaseViewHolder(ViewDataBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public void bind(Object obj) {
    binding.setVariable(BR.obj, obj);
    binding.executePendingBindings();
  }
}
