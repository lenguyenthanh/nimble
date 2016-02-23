package com.lenguyenthanh.nimble.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.lenguyenthanh.nimble.NimblePresenter;
import com.lenguyenthanh.nimble.NimbleView;
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

public class TestNimbleLinearLayout {

  @Mock
  NimblePresenter<NimbleView> presenter;
  @Mock
  Bundle bundle;
  NimbleLinearLayout<NimbleView> frameLayout;
  @Mock
  Context context;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    TestLayout tempFrameLayout = new TestLayout(context);
    frameLayout = spy(tempFrameLayout);
    ((TestLayout) frameLayout).setPresenter(presenter);
  }

  @Test
  public void testOnAttachedToWindow() throws Exception {
    frameLayout.onAttachedToWindow();
    verify(frameLayout).isInEditMode();
  }

  @Test
  public void onAttachedToWindow_inEditMode_doNothing() throws Exception {
    when(frameLayout.isInEditMode()).thenReturn(true);
    frameLayout.onAttachedToWindow();
    verifyZeroInteractions(presenter);
  }

  @Test
  public void onAttachedToWindow_notInEditMode_presenterTakeView() throws Exception {
    when(frameLayout.isInEditMode()).thenReturn(false);
    frameLayout.onAttachedToWindow();
    verify(presenter).takeView(frameLayout);
  }

  private void prepareForOnDetachedFromWindows(boolean isFinish){
    Activity activity = mock(Activity.class);
    when(activity.isFinishing()).thenReturn(isFinish);
    when(frameLayout.getActivity()).thenReturn(activity);
  }

  @Test
  public void testOnDetachedFromWindow() throws Exception {
    prepareForOnDetachedFromWindows(false);
    frameLayout.onDetachedFromWindow();
    verify(presenter).dropView(frameLayout);
  }

  @Test
  public void onDetachedFromWindow_isFinish_presenterOnDestroy() throws Exception {
    prepareForOnDetachedFromWindows(true);
    frameLayout.onDetachedFromWindow();
    verify(presenter).onDestroy();
  }

  @Test
  public void onDetachedFromWindow_isNotFinish_nothing() throws Exception {
    prepareForOnDetachedFromWindows(false);
    frameLayout.onDetachedFromWindow();
    verify(presenter).dropView(frameLayout);
    verifyNoMoreInteractions(presenter);
  }

  @Test
  public void testOnSaveInstanceState() throws Exception {
    Bundle bundle = (Bundle)frameLayout.onSaveInstanceState();
    verify(presenter).onSave(bundle);
  }

  @Test
  public void testOnRestoreInstanceState() throws Exception {
    Bundle bundle = mock(Bundle.class);
    frameLayout.onRestoreInstanceState(bundle);
    verify(bundle).getParcelable(TestLayout.PARENT_STATE_KEY);
    verify(presenter).onCreate(bundle);
  }

  public static class TestLayout extends NimbleLinearLayout<NimbleView>{

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
