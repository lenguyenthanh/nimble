package com.lenguyenthanh.nimbledagger2;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
@Singleton
public class AppModule {
  DaggerApplication mApplication;

  public AppModule(DaggerApplication application) {
    mApplication = application;
  }

  @Provides
  @Singleton
  DaggerApplication providesApplication() {
    return mApplication;
  }
}
