package com.lenguyenthanh.nimbledagger2.ui.main;

import com.lenguyenthanh.nimbledagger2.DaggerApplication;
import com.lenguyenthanh.nimbledagger2.dagger.ActivityScope;

@dagger.Component(dependencies = DaggerApplication.AppComponent.class,
    modules = MainActivityModule.class)
@ActivityScope
public interface MainActivityComponent {
  void inject(MainActivity mainActivity);
}

