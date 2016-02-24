package com.lenguyenthanh.nimbledagger2.ui.base;

import butterknife.ButterKnife;
import com.lenguyenthanh.nimble.NimbleView;
import com.lenguyenthanh.nimble.view.NimbleActivity;
import com.lenguyenthanh.nimbledagger2.DaggerApplication;

public abstract class BaseActivity<V extends NimbleView> extends NimbleActivity<V>
    implements NimbleView {

  @Override
  public void onContentChanged() {
    super.onContentChanged();
    ButterKnife.bind(this);
  }

  @Override
  protected void onDestroy() {
    ButterKnife.unbind(this);
    super.onDestroy();
  }

  @Override
  protected void initialize() {
    super.initialize();
    setupActivityComponent();
  }

  protected void setupActivityComponent() {
    buildComponent(DaggerApplication.get(this).getAppComponent());
  }

  abstract protected void buildComponent(DaggerApplication.AppComponent appComponent);
}