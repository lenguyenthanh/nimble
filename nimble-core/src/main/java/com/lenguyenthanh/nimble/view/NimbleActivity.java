package com.lenguyenthanh.nimble.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import com.lenguyenthanh.nimble.NimbleView;
import com.lenguyenthanh.nimble.NimblePresenter;

public abstract class NimbleActivity<V extends NimbleView> extends Activity implements NimbleView {

  abstract protected NimblePresenter<V> presenter();

  abstract protected int layoutId();

  @Override
  @CallSuper
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initialize();
    presenter().takeView(getMvpView());
    presenter().onCreate(savedInstanceState);
  }

  @CallSuper
  protected void initialize() {
    setContentView(layoutId());
  }

  @Override
  public void setContentView(int layoutResID) {
    if (layoutResID != 0) {
      super.setContentView(layoutResID);
    }
  }

  @Override
  @CallSuper
  protected void onResume() {
    super.onResume();
    presenter().takeView(getMvpView());
  }

  @Override
  @CallSuper
  protected void onPause() {
    presenter().dropView(getMvpView());
    super.onPause();
  }

  @Override
  @CallSuper
  protected void onSaveInstanceState(final Bundle outState) {
    presenter().onSave(outState);
    super.onSaveInstanceState(outState);
  }

  @Override
  @CallSuper
  protected void onDestroy() {
    presenter().onDestroy();
    super.onDestroy();
  }

  private V getMvpView() {
    return (V) this;
  }
}