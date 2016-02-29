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
