/*
 * Copyright 2016 Thanh Le.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

public class TestNimbleRelativeLayout {

  @Mock
  NimblePresenter<NimbleView> presenter;
  @Mock
  Bundle bundle;
  NimbleRelativeLayout<NimbleView> layout;
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

  public static class TestLayout extends NimbleRelativeLayout<NimbleView> {

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
