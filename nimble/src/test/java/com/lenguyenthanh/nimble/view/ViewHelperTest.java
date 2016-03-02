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
