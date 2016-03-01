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
package com.lenguyenthanh.nimble;

import android.os.Bundle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class NimblePresenterTest {
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
    presenter = spy(presenter);
    presenter.takeView(view);
    presenter.takeView(anotherView);
    assertThat(presenter.hasView()).isTrue();
    assertThat(anotherView).isEqualTo(presenter.getView());
    verify(presenter).dropView(view);
  }

  @Test
  public void takeView_addMultipleSameViews_work() throws Exception {
    presenter = spy(presenter);
    presenter.takeView(view);
    presenter.takeView(view);
    assertThat(presenter.hasView()).isTrue();
    assertThat(view).isEqualTo(presenter.getView());
    verify(presenter, never()).dropView(view);
  }

  @Test
  public void takeView_addNullView_work() throws Exception {
    presenter = spy(presenter);
    presenter.takeView(view);
    presenter.takeView(null);
    assertThat(presenter.hasView()).isFalse();
    assertThat(view).isNotEqualTo(presenter.getView());
    verify(presenter).dropView(view);
  }

  @Test
  public void takeView_null_work() throws Exception {
    presenter.takeView(null);
    assertThat(presenter.hasView()).isFalse();
  }

  @Test
  public void dropView_normal_work() throws Exception {
    presenter.takeView(view);
    presenter.dropView(view);
    assertThat(presenter.hasView()).isFalse();
    assertThat(presenter.getView()).isNull();
  }

  @Test
  public void dropView_differentView_work() throws Exception {
    presenter.takeView(view);
    presenter.dropView(anotherView);
    assertThat(presenter.hasView()).isTrue();
    assertThat(presenter.getView()).isEqualTo(view);
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
