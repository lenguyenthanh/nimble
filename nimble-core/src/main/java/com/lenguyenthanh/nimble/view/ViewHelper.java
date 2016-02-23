package com.lenguyenthanh.nimble.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

public class ViewHelper {
  private ViewHelper() {
  }

  private static ViewHelper instance;

  public synchronized static ViewHelper getInstance() {
    if(instance == null){
      instance = new ViewHelper();
    }
    return instance;
  }

  public Activity getActivity(Context context) {
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
