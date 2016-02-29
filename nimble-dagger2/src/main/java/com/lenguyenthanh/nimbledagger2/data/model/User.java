package com.lenguyenthanh.nimbledagger2.data.model;

public class User {
  public final String firstName;
  public final String lastName;

  public User(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return firstName + " " + lastName;
  }
}
