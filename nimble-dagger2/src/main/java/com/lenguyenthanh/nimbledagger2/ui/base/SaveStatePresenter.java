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
