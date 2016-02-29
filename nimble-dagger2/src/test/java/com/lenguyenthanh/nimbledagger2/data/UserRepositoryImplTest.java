package com.lenguyenthanh.nimbledagger2.data;

import com.lenguyenthanh.nimbledagger2.data.model.User;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserRepositoryImplTest {
  UserRepositoryImpl userRepository;

  @Before
  public void setUp() throws Exception {
    userRepository = new UserRepositoryImpl();
  }

  @Test
  public void testGetUser() throws Exception {
    User user = userRepository.getUser();
    Assert.assertNotNull(user);
  }
}
