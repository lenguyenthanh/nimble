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
    presenter().takeView(getNimbleView());
    presenter().onCreate(savedInstanceState);
  }

  @CallSuper
  protected void initialize() {
    setContentView(layoutId());
  }

  @Override
  @CallSuper
  protected void onResume() {
    super.onResume();
    presenter().takeView(getNimbleView());
  }

  @Override
  @CallSuper
  protected void onPause() {
    presenter().dropView(getNimbleView());
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

  private V getNimbleView() {
    return (V) this;
  }
}