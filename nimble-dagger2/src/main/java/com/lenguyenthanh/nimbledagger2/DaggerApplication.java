package com.lenguyenthanh.nimbledagger2;

import android.app.Application;
import android.content.Context;
import dagger.Component;
import javax.inject.Singleton;

public class DaggerApplication extends Application{
  protected AppComponent appComponent;

  public AppComponent getAppComponent() {
    return appComponent;
  }

  public static DaggerApplication get(Context context) {
    return (DaggerApplication) context.getApplicationContext();
  }

  @Override
  public void onCreate() {
    super.onCreate();
    initializeDaggerComponent();
  }

  protected void initializeDaggerComponent() {
    appComponent = DaggerDaggerApplication_AppComponent.builder()
        .appModule(new AppModule(this))
        .build();
    appComponent.inject(this);
  }

  @Singleton
  @Component(modules = AppModule.class)
  public interface AppComponent {
    DaggerApplication application();

    void inject(DaggerApplication app);
  }
}
