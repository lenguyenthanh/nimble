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
package com.lenguyenthanh.nimbleexample.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.lenguyenthanh.nimble.BasePresenter;
import com.lenguyenthanh.nimbleexample.data.UserRepository;
import com.lenguyenthanh.nimbleexample.data.UserRepositoryImpl;
import com.lenguyenthanh.nimbleexample.data.model.User;

public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {

  @NonNull
  private final UserRepository userRepository;

  public MainPresenterImpl() {
    this.userRepository = new UserRepositoryImpl();
  }

  @Override
  public void onSave(Bundle outState) {
    // This method is intentionally empty.
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    // This method is intentionally empty.
  }

  @Override
  public void getUser() {
    User user = userRepository.getUser();
    getView().sayHello(user.toString());
  }

  @Override
  public void onDestroy() {
    // This method is intentionally empty.
  }
}
