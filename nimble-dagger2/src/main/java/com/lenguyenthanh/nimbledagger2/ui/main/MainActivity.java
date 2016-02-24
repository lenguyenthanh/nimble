package com.lenguyenthanh.nimbledagger2.ui.main;

import com.lenguyenthanh.nimbledagger2.DaggerApplication;
import com.lenguyenthanh.nimbledagger2.R;
import com.lenguyenthanh.nimbledagger2.ui.base.BaseActivity;
import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainView> {

  @Inject
  MainPresenter presenter;

  @Override
  protected MainPresenter presenter() {
    return presenter;
  }

  @Override
  protected int layoutId() {
    return R.layout.activity_main;
  }

  @Override
  protected void buildComponent(DaggerApplication.AppComponent appComponent) {
    MainActivityComponent component = DaggerMainActivityComponent.builder()
        .mainActivityModule(new MainActivityModule(this))
        .appComponent(appComponent)
        .build();
    component.inject(this);
  }
}
