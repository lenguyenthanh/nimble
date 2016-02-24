package com.lenguyenthanh.nimbledagger2.ui.main;

import com.lenguyenthanh.nimbledagger2.data.UserRepository;
import com.lenguyenthanh.nimbledagger2.data.model.User;
import com.lenguyenthanh.nimbledagger2.ui.base.SaveStatePresenter;
import javax.inject.Inject;

public class MainPresenterImpl extends SaveStatePresenter<MainView> implements MainPresenter {
  private final UserRepository userRepository;

  @Inject
  public MainPresenterImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void getUser(){
    User user = userRepository.getUser();
    getView().showUser(user);
  }

  @Override
  public void onDestroy() {
    // This method is intentionally empty.
  }
}
