package com.example.joao.berrantinho.features.toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.joao.berrantinho.BaseFragment;
import com.example.joao.berrantinho.R;

/**
 * Created by Joao Carlos on 11/24/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class ToolbarFragment extends BaseFragment implements View.OnClickListener {

  Button btn;

  public static ToolbarFragment newInstance() {
    return new ToolbarFragment();
  }

  @Override
  public int getFragmentLayout() {
    return R.layout.fragment_toolbar;
  }

  @Override
  public void setUpCustomViews(View rootView) {
    btn = rootView.findViewById(R.id.btn_toolbar);
  }

  @Override
  public void setUpLayoutListeners() {
    btn.setOnClickListener(this);
  }

  @Override
  public void initDependencies() {

  }

  @Override
  public void updateUI() {

  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_toolbar:
        Toast.makeText(getContext(), "btn toolbar", Toast.LENGTH_SHORT).show();
        break;
    }
  }
}
