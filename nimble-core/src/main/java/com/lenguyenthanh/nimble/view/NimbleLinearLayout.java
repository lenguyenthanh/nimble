package com.lenguyenthanh.nimble.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.lenguyenthanh.nimble.NimbleView;
import com.lenguyenthanh.nimble.NimblePresenter;

public abstract class NimbleLinearLayout<V extends NimbleView> extends LinearLayout implements
    NimbleView {
  private static final String PARENT_STATE_KEY = "parent_state";

  abstract protected NimblePresenter<V> presenter();

  public NimbleLinearLayout(Context context) {
    super(context);
  }

  public NimbleLinearLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public NimbleLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public Activity getActivity() {
    Context context = getContext();
    while (!(context instanceof Activity) && context instanceof ContextWrapper) {
      context = ((ContextWrapper) context).getBaseContext();
    }
    if (!(context instanceof Activity)) {
      throw new IllegalStateException(
          "Expected an activity context, got " + context.getClass().getSimpleName());
    }
    return (Activity) context;
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
    if(getActivity().isFinishing()){
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
    Bundle bundle = (Bundle)state;
    super.onRestoreInstanceState(bundle.getParcelable(PARENT_STATE_KEY));
    presenter().onCreate(bundle);
  }

  private V getMvpView() {
    return (V) this;
  }
}
