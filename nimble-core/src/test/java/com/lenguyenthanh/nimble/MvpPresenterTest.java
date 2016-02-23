package com.lenguyenthanh.nimble;

import android.os.Bundle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.google.common.truth.Truth.assertThat;

public class MvpPresenterTest {
  @Mock
  NimbleView view;
  @Mock
  NimbleView anotherView;

  BasePresenter<NimbleView> presenter;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    presenter = new TestPresenter();
  }

  @Test
  public void takeView_normal_work() throws Exception {
    presenter.takeView(view);
    assertThat(presenter.hasView()).isTrue();
    assertThat(view).isEqualTo(presenter.getView());
  }

  @Test
  public void takeView_addMultipleViews_work() throws Exception {
    presenter.takeView(view);
    presenter.takeView(anotherView);
    assertThat(presenter.hasView()).isTrue();
    assertThat(anotherView).isEqualTo(presenter.getView());
  }

  @Test
  public void dropView_normal_work() throws Exception {
    presenter.takeView(view);
    presenter.dropView(view);
    assertThat(presenter.hasView()).isFalse();
    assertThat(presenter.getView()).isNull();
  }

  public static class TestPresenter extends BasePresenter<NimbleView> {

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSave(Bundle outState) {

    }
  }
}
