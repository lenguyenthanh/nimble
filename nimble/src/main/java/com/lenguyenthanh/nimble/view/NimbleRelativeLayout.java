package com.lenguyenthanh.nimble.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.lenguyenthanh.nimble.NimblePresenter;
import com.lenguyenthanh.nimble.NimbleView;

public abstract class NimbleRelativeLayout<V extends NimbleView> extends LinearLayout
    implements NimbleView {
  protected static final String PARENT_STATE_KEY = "parent_state";

  abstract protected NimblePresenter<V> presenter();

  public NimbleRelativeLayout(Context context) {
    super(context);
  }

  public NimbleRelativeLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public NimbleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public Activity getActivity() {
    return ViewHelper.getInstance().getActivity(getContext());
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    if (!isInEditMode()) {
      presenter().takeView(getMvpView());
    }
  }

  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    presenter().dropView(getMvpView());
    if (getActivity().isFinishing()) {
      presenter().onDestroy();
    }
  }

  @Override
  protected Parcelable onSaveInstanceState() {
    Bundle outState = new Bundle();
    outState.putParcelable(PARENT_STATE_KEY, super.onSaveInstanceState());
    presenter().onSave(outState);
    return outState;
  }

  @Override
  protected void onRestoreInstanceState(Parcelable state) {
    Bundle bundle = (Bundle) state;
    super.onRestoreInstanceState(bundle.getParcelable(PARENT_STATE_KEY));
    presenter().onCreate(bundle);
  }

  private V getMvpView() {
    return (V) this;
  }
}
