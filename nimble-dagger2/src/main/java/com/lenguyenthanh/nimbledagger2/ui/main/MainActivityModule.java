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
