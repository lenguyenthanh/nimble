package com.lenguyenthanh.nimbledagger2.ui.main;

import com.lenguyenthanh.nimbledagger2.ui.base.SaveStatePresenter;
import javax.inject.Inject;

public class MainPresenterImpl extends SaveStatePresenter<MainView> implements MainPresenter {
  @Inject
  public MainPresenterImpl() {
    // This constructor is to help Dagger.
  }
}
