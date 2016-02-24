package com.lenguyenthanh.nimbledagger2.ui.main;

import android.app.Activity;
import com.lenguyenthanh.nimbledagger2.data.UserRepository;
import com.lenguyenthanh.nimbledagger2.data.UserRepositoryImpl;
import com.lenguyenthanh.nimbledagger2.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
  private final MainActivity mainActivity;

  public MainActivityModule(MainActivity mainActivity) {
    this.mainActivity = mainActivity;
  }

  @Provides
  @ActivityScope
  Activity provideActivity() {
    return mainActivity;
  }

  @Provides
  @ActivityScope
  MainPresenter providePresenter(MainPresenterImpl presenter) {
    return presenter;
  }

  @Provides
  @ActivityScope
  UserRepository provideUserRepository(){
    return new UserRepositoryImpl();
  }
}
