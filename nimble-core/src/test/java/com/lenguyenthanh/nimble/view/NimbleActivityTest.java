package com.lenguyenthanh.nimble.view;

import android.os.Bundle;
import com.lenguyenthanh.nimble.BuildConfig;
import com.lenguyenthanh.nimble.NimblePresenter;
import com.lenguyenthanh.nimble.NimbleView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19)
public class NimbleActivityTest {


  @Mock
  NimblePresenter<NimbleView> presenter;
  TestActivity activity;
  ActivityController controller;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    controller = Robolectric.buildActivity(TestActivity.class);
    activity = (TestActivity) controller.get();
    activity.setPresenter(presenter);
    controller.create();
    reset(presenter);
  }

  @Test
  public void testOnCreate() throws Exception {
    controller.create();
    verify(presenter).takeView(Mockito.any(NimbleView.class));
    verify(presenter).onCreate(any(Bundle.class));
  }

  @Test
  public void testOnResume() throws Exception {
    controller.resume();
    verify(presenter).takeView(activity);
  }

  @Test
  public void testOnPause() throws Exception {
    controller.pause();
    verify(presenter).dropView(activity);
  }

  @Test
  public void testOnSaveInstanceState() throws Exception {
    Bundle bundle = Mockito.mock(Bundle.class);
    controller.saveInstanceState(bundle);
    verify(presenter).onSave(bundle);
  }

  @Test
  public void testOnDestroy() throws Exception {
    controller.destroy();
    verify(presenter).onDestroy();
  }

  public static class TestActivity extends NimbleActivity<NimbleView> {

    private NimblePresenter<NimbleView> presenter;

    public void setPresenter(NimblePresenter<NimbleView> presenter) {
      this.presenter = presenter;
    }

    @Override
    protected NimblePresenter<NimbleView> presenter() {
      return presenter;
    }

    @Override
    protected int layoutId() {
      return 0;
    }
  }
}
