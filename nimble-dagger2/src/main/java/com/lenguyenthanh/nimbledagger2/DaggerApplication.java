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
