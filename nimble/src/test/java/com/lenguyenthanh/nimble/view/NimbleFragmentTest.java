package com.lenguyenthanh.nimble.view;

import android.os.Bundle;
import com.lenguyenthanh.nimble.NimblePresenter;
import com.lenguyenthanh.nimble.NimbleView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class NimbleFragmentTest {
  @Mock
  NimblePresenter<NimbleView> presenter;
  @Mock
  Bundle bundle;
  NimbleFragment<NimbleView> fragment;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    fragment = spy(TestFragment.class);
    ((TestFragment) fragment).setPresenter(presenter);
  }

  //@Test
  //public void testOnCreateView() throws Exception {
  //  LayoutInflater layoutInflater = spy(LayoutInflater.class);
  //  ViewGroup viewGroup = spy(ViewGroup.class);
  //  layout.onCreateView(layoutInflater, viewGroup, bundle);
  //  verify(layoutInflater).inflate(layout.layoutId(), viewGroup, false);
  //  verify(presenter).takeView(layout);
  //  verify(presenter).onCreate(bundle);
  //}

  @Test
  public void testOnResume() throws Exception {
    fragment.onResume();
    verify(presenter).takeView(fragment);
  }

  @Test
  public void testOnPause() throws Exception {
    fragment.onPause();
    verify(presenter).dropView(fragment);
  }

  @Test
  public void testOnSaveInstanceState() throws Exception {
    fragment.onSaveInstanceState(bundle);
    verify(presenter).onSave(bundle);
  }

  @Test
  public void testOnDestroy() throws Exception {
    fragment.onDestroy();
    verify(presenter).onDestroy();
  }

  public static class TestFragment extends NimbleFragment<NimbleView> {

    NimblePresenter<NimbleView> presenter;

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
