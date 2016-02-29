package com.lenguyenthanh.nimble.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import junit.framework.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ViewHelperTest {
  @Test
  public void testGetInstance() throws Exception {
    ViewHelper viewHelper = ViewHelper.getInstance();
    Assert.assertNotNull(viewHelper);
  }

  @Test
  public void testGetActivity() throws Exception {
    Activity spy = spy(Activity.class);
    Activity result = ViewHelper.getInstance().getActivity(spy);
    Assert.assertEquals(result, spy);
  }

  @Test
  public void getActivity_contextWrapper() throws Exception {
    Activity spy = spy(Activity.class);
    ContextWrapper wrapper = spy(new ContextWrapper(spy));
    when(wrapper.getBaseContext()).thenReturn(spy);
    Activity result = ViewHelper.getInstance().getActivity(wrapper);
    Assert.assertEquals(result, spy);
  }

  @Test(expected = IllegalStateException.class)
  public void getActivity_exception() throws Exception {
    Context spy = Mockito.spy(Context.class);
    ViewHelper.getInstance().getActivity(spy);
  }
}
