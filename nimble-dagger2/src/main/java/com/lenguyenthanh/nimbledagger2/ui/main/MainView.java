package com.lenguyenthanh.nimbledagger2.ui.main;

import com.lenguyenthanh.nimble.NimbleView;
import com.lenguyenthanh.nimbledagger2.data.model.User;

public interface MainView extends NimbleView {
  void showUser(User user);
}
