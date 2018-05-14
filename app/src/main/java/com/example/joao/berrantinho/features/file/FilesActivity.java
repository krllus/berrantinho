package com.example.joao.berrantinho.features.file;

import com.example.joao.berrantinho.BaseActivity;
import com.example.joao.berrantinho.BaseFragment;
import com.example.joao.berrantinho.R;

public class FilesActivity extends BaseActivity {

    @Override
    public int getActivityTitleResourceId() {
        return R.string.activity_files;
    }

    @Override
    public void initDependencies() {
        updateUI();
    }

    private void updateUI() {
        BaseFragment fragment = FilesFragment.newInstance();
        loadContentFragment(fragment);
    }
}
