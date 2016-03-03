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
package com.lenguyenthanh.nimbleexample.ui.main;

import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.lenguyenthanh.nimble.view.NimbleActivity;
import com.lenguyenthanh.nimbleexample.R;

public class MainActivity extends NimbleActivity<MainView> implements MainView {

  @Bind(R.id.tvContent)
  TextView tvContent;

  private MainPresenter presenter;

  @Override
  protected MainPresenter presenter() {
    if (presenter == null) {
      presenter = createPresenter();
    }
    return presenter;
  }

  private MainPresenter createPresenter() {
    return new MainPresenterImpl();
  }

  @Override
  protected int layoutId() {
    return R.layout.activity_main;
  }

  @OnClick(R.id.fab)
  void fabClicked() {
    presenter().getUser();
  }

  @Override
  public void sayHello(String name) {
    tvContent.setText(String.format("Hello %s", name));
  }

  @Override
  public void onContentChanged() {
    super.onContentChanged();
    ButterKnife.bind(this);
  }

  @Override
  protected void onDestroy() {
    ButterKnife.unbind(this);
    super.onDestroy();
  }
}
