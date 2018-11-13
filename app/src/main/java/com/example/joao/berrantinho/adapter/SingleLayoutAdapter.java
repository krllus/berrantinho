package com.example.joao.berrantinho.adapter;

/**
 * Created by Joao Carlos on 12/12/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public abstract class SingleLayoutAdapter<T> extends MyBaseAdapter<T> {
  private final int layoutId;

  public SingleLayoutAdapter(int layoutId) {
    this.layoutId = layoutId;
  }

  @Override
  protected int getLayoutIdForPosition(int position) {
    return layoutId;
  }
}
