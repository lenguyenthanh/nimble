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

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.lenguyenthanh.nimbledagger2.data.UserRepository;
import com.lenguyenthanh.nimbledagger2.data.model.User;
import com.lenguyenthanh.nimbledagger2.ui.base.SaveStatePresenter;
import icepick.State;
import javax.inject.Inject;

public class MainPresenterImpl extends SaveStatePresenter<MainView> implements MainPresenter {
  @NonNull
  private final UserRepository userRepository;
  @State
  String fullName;

  @Inject
  public MainPresenterImpl(@NonNull UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    restoreData();
  }

  private void restoreData() {
    if (fullName != null && fullName.length() != 0) {
      getView().sayHello(fullName);
    }
  }

  @Override
  public void getUser() {
    User user = userRepository.getUser();
    fullName = user.toString();
    getView().sayHello(fullName);
  }

  @Override
  public void onDestroy() {
    // This method is intentionally empty.
  }
}
