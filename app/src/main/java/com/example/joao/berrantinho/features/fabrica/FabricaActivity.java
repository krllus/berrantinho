package com.example.joao.berrantinho.features.fabrica;

import com.example.joao.berrantinho.BaseActivity;
import com.example.joao.berrantinho.BaseFragment;
import com.example.joao.berrantinho.R;

/**
 * Created by Joao Carlos on 11/27/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */


public class FabricaActivity extends BaseActivity {
    @Override
    public int getActivityTitleResourceId() {
        return R.string.activity_fabrica;
    }

    @Override
    public void initDependencies() {
        updateUI();
    }

    public void updateUI() {
        BaseFragment fragment = FabricaFragment.newInstance();
        loadContentFragment(fragment);
    }
}
