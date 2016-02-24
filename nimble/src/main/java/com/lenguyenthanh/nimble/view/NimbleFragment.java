package com.lenguyenthanh.nimble.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lenguyenthanh.nimble.NimbleView;
import com.lenguyenthanh.nimble.NimblePresenter;

public abstract class NimbleFragment<V extends NimbleView> extends Fragment implements NimbleView {

  abstract protected NimblePresenter<V> presenter();

  abstract protected int layoutId();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(layoutId(), container, false);
    presenter().takeView(getMvpView());
    presenter().onCreate(savedInstanceState);
    return view;
  }

  @Override
  @CallSuper
  public void onResume() {
    super.onResume();
    presenter().takeView(getMvpView());
  }

  @Override
  @CallSuper
  public void onPause() {
    presenter().dropView(getMvpView());
    super.onPause();
  }

  @Override
  public void onDestroy() {
    presenter().onDestroy();
    super.onDestroy();
  }

  @Override
  @CallSuper
  public void onSaveInstanceState(final Bundle outState) {
    presenter().onSave(outState);
    super.onSaveInstanceState(outState);
  }

  private V getMvpView() {
    return (V) this;
  }
}
