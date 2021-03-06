package com.example.joao.berrantinho.features.notification;

import com.example.joao.berrantinho.BaseActivity;
import com.example.joao.berrantinho.BaseFragment;
import com.example.joao.berrantinho.R;

/**
 * Created by Joao Carlos on 11/24/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */

public class NotificationActivity extends BaseActivity {

  @Override
  public int getActivityTitleResourceId() {
    return R.string.activity_notification;
  }

  @Override
  public void initDependencies() {
    updateUI();
  }

  private void updateUI() {
    BaseFragment fragment = NotificationFragment.newInstance();
    loadContentFragment(fragment);
  }
}
