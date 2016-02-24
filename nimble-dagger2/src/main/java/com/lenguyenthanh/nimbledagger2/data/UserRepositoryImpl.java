package com.lenguyenthanh.nimbledagger2.data;

import com.lenguyenthanh.nimbledagger2.data.model.User;

public class UserRepositoryImpl implements UserRepository {
  @Override
  public User getUser() {
    return new User("Nimble", "Library");
  }
}
