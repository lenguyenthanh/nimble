package com.lenguyenthanh.nimbledagger2.data.model;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
  String firstName = "Nimble";
  String lastName = "Library";
  User user;

  @Before
  public void setUp() throws Exception {
    user = new User(firstName, lastName);

  }

  @Test
  public void testUser() throws Exception {
    Assert.assertEquals(firstName, user.firstName);
    Assert.assertEquals(lastName, user.lastName);
  }

  @Test
  public void testToString() throws Exception {
    Assert.assertEquals(firstName + " " + lastName, user.toString());
  }
}
