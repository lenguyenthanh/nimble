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
import android.content.ContextWrapper;

public final class ViewHelper {

  private static ViewHelper instance;

  private ViewHelper() {
  }

  public static ViewHelper getInstance() {
    synchronized (ViewHelper.class) {
      if (instance == null) {
        instance = new ViewHelper();
      }
    }
    return instance;
  }

  public Activity getActivity(final Context viewContext) {
    Context context = viewContext;
    while (!(context instanceof Activity) && context instanceof ContextWrapper) {
      context = ((ContextWrapper) context).getBaseContext();
    }
    if (!(context instanceof Activity)) {
      throw new IllegalStateException(
          "Expected an activity context, got " + context.getClass().getSimpleName());
    }
    return (Activity) context;
  }
}
