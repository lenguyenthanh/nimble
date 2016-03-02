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
