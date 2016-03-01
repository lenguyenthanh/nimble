/*
 * Copyright 2016 Thanh Le.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lenguyenthanh.nimble.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.lenguyenthanh.nimble.NimblePresenter;
import com.lenguyenthanh.nimble.NimbleView;

public abstract class NimbleLinearLayout<V extends NimbleView> extends LinearLayout
    implements NimbleView {
  protected static final String PARENT_STATE_KEY = "parent_state";

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
