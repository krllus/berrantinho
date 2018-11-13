package com.example.joao.berrantinho;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by João Carlos Ferreira Marques on 11/23/17.
 * joaocarlusferrera at gmail.com
 */

public abstract class BaseFragment extends Fragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initDependencies();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(resolveFragmentLayout(), container, false);

    setUpCustomViews(rootView);

    return rootView;
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setUpLayoutListeners();
  }

  private int resolveFragmentLayout() {
    int layout = getFragmentLayout();
    if (layout == 0) {
      throw new Error("you should provide a layout for the fragment");
    }
    return layout;
  }

  public abstract int getFragmentLayout();

  public abstract void setUpCustomViews(View rootView);

  public abstract void setUpLayoutListeners();

  public abstract void initDependencies();

  public abstract void updateUI();
}
