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
