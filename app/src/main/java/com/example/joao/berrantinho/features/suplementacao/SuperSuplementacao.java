package com.example.joao.berrantinho.features.suplementacao;

import com.example.joao.berrantinho.BaseActivity;
import com.example.joao.berrantinho.BaseFragment;
import com.example.joao.berrantinho.R;

/**
 * Created by Joao Carlos on 11/24/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class SuperSuplementacao extends BaseActivity {

  @Override
  public int getActivityTitleResourceId() {
    return R.string.activity_suplementacao;
  }

  @Override
  public void initDependencies() {
    updateUI();
  }

  private void updateUI() {
    BaseFragment suplementacaoFragment = SuplementacaoFragment.newInstance();
    loadContentFragment(suplementacaoFragment);
  }

  @Override
  public int getActivityCustomLayout() {
    return R.layout.activity_supersuplementacao;
  }
}
