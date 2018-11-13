package com.example.joao.berrantinho;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

/**
 * Created by João Carlos Ferreira Marques on 11/23/17.
 * joaocarlusferrera at gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity
    implements BaseActivityDefaultInterface {

  private final static String LOG_TAG = BaseActivity.class.getSimpleName();

  ProgressBar contentLoading;
  FrameLayout fragmentContainer;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(resolveActivityLayout());

    setUpWindowAnimations();
    setUpToolbar();

    setUpInitialContent();

    initDependencies();
  }

  @Override
  public int getActivityCustomLayout() {
    return R.layout.base_activity;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private int resolveActivityLayout() {
    int layoutId = getActivityCustomLayout();
    if (layoutId == 0) {
      throw new Error("you should pass a valid layout id as parameter");
    }
    return layoutId;
  }

  private void setUpWindowAnimations() {
  }

  private void setUpToolbar() {

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ActionBar actionBar = getSupportActionBar();

    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle(setUpTitle());
    }
  }

  private CharSequence setUpTitle() {
    int resId = getActivityTitleResourceId();
    String title;

    try {
      if (resId != 0) {
        title = getString(resId);
      } else {
        throw new Error();
      }
    } catch (Error error) {
      Log.e(LOG_TAG, "you should pass a valid string id as title");
      title = getString(R.string.app_name);
    }

    return title;
  }

  private void setUpInitialContent() {
    contentLoading = findViewById(R.id.loading_progressbar);
    fragmentContainer = findViewById(R.id.fragment_container);
    showLoadingContent();
  }

  private void showLoadingContent() {
    contentLoading.setVisibility(View.VISIBLE);
    fragmentContainer.setVisibility(View.GONE);
  }

  private void hideLoadingContent() {
    contentLoading.setVisibility(View.GONE);
    fragmentContainer.setVisibility(View.VISIBLE);
  }

  public void loadContentFragment(BaseFragment fragment) {

    Slide slideEnter = new Slide(Gravity.END);
    slideEnter.setDuration(getResources().getInteger(R.integer.anim_duration_short));
    fragment.setEnterTransition(slideEnter);

    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .commit();

    hideLoadingContent();
  }

  public abstract void initDependencies();
}
