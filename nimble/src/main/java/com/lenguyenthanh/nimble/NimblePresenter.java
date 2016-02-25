package com.lenguyenthanh.nimble;

import android.os.Bundle;

public interface NimblePresenter<View extends NimbleView> {
  void takeView(View view);

  void onSave(Bundle outState);

  void dropView(View view);

  void onCreate(Bundle savedInstanceState);

  void onDestroy();
}