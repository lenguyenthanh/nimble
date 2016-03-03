# Nimble [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.lenguyenthanh.nimble/nimble/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/cz.jirutka.rsql/rsql-parser) [![Build Status](https://travis-ci.org/lenguyenthanh/nimble.svg?branch=master)](https://travis-ci.org/lenguyenthanh/nimble) [![codecov.io](https://codecov.io/github/lenguyenthanh/nimble/coverage.svg?branch=master)](https://codecov.io/github/lenguyenthanh/nimble?branch=master)

Nimble is a small, quick and flexible Model-View-Presenter library for Android

If you do not know much about MVP, please read about it: [MVP Definition and best practices](https://github.com/lenguyenthanh/nimble/wiki/MVP).
Or read more about [Nimble](https://github.com/lenguyenthanh/nimble/wiki/Nimble).

### Why Nimble
* It has totally 500 lines of code. So it is simple and easy to work with. You don't really need to take a lot of time to understand it.
* It has well tested code so you can confident when using it.
* It is designed to be used with Dagger but you can use it any way you like. It is so flexible.
* It can be used as multiple MVP components in one screen as well as, one MVP for multiple screens.
* It manage the life cycle of the view and presenter throw base classes so you will save a lot of tim.
* It provides mechanism to save data when activity/fragment/view is recreated throws Bundle class.

### Usage
Nimble has two basic interface [NimbleView](https://github.com/lenguyenthanh/nimble/blob/master/nimble-core/src/main/java/com/lenguyenthanh/nimble/NimbleView.java) and [NimblePresenter](https://github.com/lenguyenthanh/nimble/blob/master/nimble-core/src/main/java/com/lenguyenthanh/nimble/NimblePresenter.java). They are base interfaces for ant views and presenters.

You should follow these steps to create a MVP component in your application:

1. Create a View interface which must extents NimbleView:

  ```java
  public interface MainView extends NimbleView {
      void showUser(User user);
  }
  ```
2. Create a Presenter interface which must extent NimblePresenter with View generic like:

  ```java
  public interface MainPresenter extends NimblePresenter<MainView> {
      void getUser();
  }
  ```
3. Implement your Presenter which should extent [BasePresenter](https://github.com/lenguyenthanh/nimble/blob/master/nimble-core/src/main/java/com/lenguyenthanh/nimble/BasePresenter.java):

  ```java
  public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {
      public void getUser(){
          User user = ...
          getView().showUser(user);
      }
  }
  ```
4. Implement your View which should extent base a Nimble Views such as [NimbleActivity](https://github.com/lenguyenthanh/nimble/blob/master/nimble-core/src/main/java/com/lenguyenthanh/nimble/view/NimbleActivity.java) and implments your View interface:

  ```java
  public class MainActivity extends NimbleActivity<MainView> implements MainView {
      private MainPresenter presenter;
      
      @Override
      protected MainPresenter presenter() {
          if(presenter == null){
              presenter = new MainPresenter();
          }
          return presenter;
      }
    
      void onButtonClicked(){
          presenter().getUser();
      }
  }
  ```

Congratulation!!! You have a MVP component now.

### Inspiration

Nimble has a lot of inspiration from other great MVP libraries for Android:

* Mortar - https://github.com/square/mortar
* Nucleus - https://github.com/konmik/nucleus
* Mosby - https://github.com/sockeqwe/mosby

### Installation
```gradle
compile 'com.lenguyenthanh.nimble:nimble:0.5.1'
```

### License

    Copyright (C) 2016 Thanh Le

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
