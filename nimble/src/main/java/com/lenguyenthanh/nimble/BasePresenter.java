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
