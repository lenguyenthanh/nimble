package com.lenguyenthanh.nimbledagger2.ui.main;

import android.os.Bundle;
import com.lenguyenthanh.nimbledagger2.data.UserRepository;
import com.lenguyenthanh.nimbledagger2.data.model.User;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class MainPresenterImplTest {
  MainPresenterImpl presenter;

  @Mock
  UserRepository userRepository;
  @Mock
  MainView view;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    presenter = new MainPresenterImpl(userRepository);
    presenter.takeView(view);
  }

  @Test
  public void testRestoreData() throws Exception {
    String fullName = "Nimble Library";
    presenter.fullName = fullName;
    presenter.onCreate(any(Bundle.class));
    verify(view).sayHello(fullName);
  }

  @Test
  public void testRestoreNullData() throws Exception {
    presenter.onCreate(null);
    verifyZeroInteractions(view);
  }

  @Test
  public void testRestoreEmptyData() throws Exception {
    String fullName = "";
    presenter.fullName = fullName;
    presenter.onCreate(null);
    verifyZeroInteractions(view);
  }

  @Test
  public void testGetUser() throws Exception {
    User user = new User("Nimble", "Library");
    when(userRepository.getUser()).thenReturn(user);
    presenter.getUser();
    verify(userRepository).getUser();
    Assert.assertEquals(user.toString(), presenter.fullName);
    verify(view).sayHello(presenter.fullName);
  }

  @Test
  public void testOnDestroy() throws Exception {
    presenter.onDestroy();
    verifyZeroInteractions(view);
  }
}
