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
package com.lenguyenthanh.nimbledagger2.ui.base;

import android.os.Bundle;
import com.lenguyenthanh.nimble.BasePresenter;
import com.lenguyenthanh.nimble.NimbleView;
import icepick.Icepick;

public abstract class SaveStatePresenter<View extends NimbleView> extends BasePresenter<View> {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    Icepick.restoreInstanceState(this, savedInstanceState);
  }

  @Override
  public void onSave(final Bundle outState) {
    Icepick.saveInstanceState(this, outState);
  }
}
