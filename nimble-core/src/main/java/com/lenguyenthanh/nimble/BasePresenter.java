package com.lenguyenthanh.nimble;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

public abstract class BasePresenter<View extends NimbleView> implements NimblePresenter<View> {
  private View view = null;

  protected final boolean hasView() {
    return view != null;
  }

  protected final View getView() {
    return view;
  }
  
  @Override
  public void takeView(View view) {
    if (this.view != view) {
      if (this.view != null) {
        dropView(this.view);
      }
      this.view = view;
    }
  }

  @Override
  @CallSuper
  public void dropView(@NonNull final View view) {
    if (view == this.view) {
      this.view = null;
    }
  }
}
