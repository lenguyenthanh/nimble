package com.lenguyenthanh.nimble.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.lenguyenthanh.nimble.NimblePresenter;
import com.lenguyenthanh.nimble.NimbleView;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class TestNimbleFrameLayout {

  @Mock
  NimblePresenter<NimbleView> presenter;
  @Mock
  Bundle bundle;
  NimbleFrameLayout<NimbleView> layout;
  @Mock
  Context context;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    TestLayout tempFrameLayout = new TestLayout(context);
    layout = spy(tempFrameLayout);
    ((TestLayout) layout).setPresenter(presenter);
  }

  @Test
  public void testOnAttachedToWindow() throws Exception {
    layout.onAttachedToWindow();
    verify(layout).isInEditMode();
  }

  @Test
  public void onAttachedToWindow_inEditMode_doNothing() throws Exception {
    when(layout.isInEditMode()).thenReturn(true);
    layout.onAttachedToWindow();
    verifyZeroInteractions(presenter);
  }

  @Test
  public void onAttachedToWindow_notInEditMode_presenterTakeView() throws Exception {
    when(layout.isInEditMode()).thenReturn(false);
    layout.onAttachedToWindow();
    verify(presenter).takeView(layout);
  }

  private void prepareForOnDetachedFromWindows(boolean isFinish) {
    Activity activity = mock(Activity.class);
    when(activity.isFinishing()).thenReturn(isFinish);
    when(layout.getActivity()).thenReturn(activity);
  }

  @Test
  public void testOnDetachedFromWindow() throws Exception {
    prepareForOnDetachedFromWindows(false);
    layout.onDetachedFromWindow();
    verify(presenter).dropView(layout);
  }

  @Test
  public void onDetachedFromWindow_isFinish_presenterOnDestroy() throws Exception {
    prepareForOnDetachedFromWindows(true);
    layout.onDetachedFromWindow();
    verify(presenter).onDestroy();
  }

  @Test
  public void onDetachedFromWindow_isNotFinish_nothing() throws Exception {
    prepareForOnDetachedFromWindows(false);
    layout.onDetachedFromWindow();
    verify(presenter).dropView(layout);
    verifyNoMoreInteractions(presenter);
  }

  @Test
  public void testOnSaveInstanceState() throws Exception {
    Bundle bundle = (Bundle) layout.onSaveInstanceState();
    verify(presenter).onSave(bundle);
  }

  @Test
  public void testOnRestoreInstanceState() throws Exception {
    Bundle bundle = mock(Bundle.class);
    layout.onRestoreInstanceState(bundle);
    verify(bundle).getParcelable(TestLayout.PARENT_STATE_KEY);
    verify(presenter).onCreate(bundle);
  }

  @Test
  public void testGetActivity() throws Exception {
    Activity activity = layout.getActivity();
    Assert.assertNotNull(activity);
  }

  public static class TestLayout extends NimbleFrameLayout<NimbleView> {

    NimblePresenter<NimbleView> presenter;

    @Override
    public Activity getActivity() {
      return mock(Activity.class);
    }

    public void setPresenter(NimblePresenter<NimbleView> presenter) {
      this.presenter = presenter;
    }

    public TestLayout(Context context) {
      super(context);
    }

    @Override
    protected NimblePresenter<NimbleView> presenter() {
      return presenter;
    }
  }
}
