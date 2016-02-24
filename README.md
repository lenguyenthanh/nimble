# Nimble [![Build Status](https://travis-ci.org/lenguyenthanh/nimble.svg?branch=master)](https://travis-ci.org/lenguyenthanh/nimble) [![codecov.io](https://codecov.io/github/lenguyenthanh/nimble/coverage.svg?branch=master)](https://codecov.io/github/lenguyenthanh/nimble?branch=master)

Nimble is a small, quick and flexible Model-View-Presenter library for Android

### Android Model-View-Presenter(Mvp)
Mvp is a architectural pattern, and is to used mostly for front end. It has three main parts:
* The **model** should be a simple Java object, is used to store data to present to user.
* The **View** is used to display data to user. It should be a [passive view](http://martinfowler.com/eaaDev/PassiveScreen.html).
* The **presenter** acts upon the model and the view. It retrieves data from repositories (the model), and formats it for display in the view.

There are some other definitions which specifics for Android (Thanks [Christian](https://twitter.com/panavtec) for his great [article](http://panavtec.me/modeling-presentation-layer/)):
* **Android view**: Just an Android component, something that extends from android.view.View
* **View**: The view interface to communicate from your presenter to your view implementation, it can be implemented in your preferred Android component, sometimes is better to use an Activity others a Fragment or maybe a Custom View.
* **Screen**: A screen is more a user concept, the user gets the feeling that the phone is navigating between windows, but we can represent this in Android with Activities or replacing fragments/views in the same Activity. So it depends on the perception that the user gets and usually represents all the content that you can see in the view.

### Why Nimble
* It has totally 500 lines of code. So it is simple and easy to work with. You don't really need to take alot of time to understand it.
* It has well tested code so you can confident when using it.
* It is designed to be used with Dagger but you can use it any way you like. It is so flexible.
* It can be used as multiple Mvps in one screen as well as, one Mvp for multiple screens.
* It provides mechanism to save data when activity/fragment/view is recreated throws Bundle class.

### Usage
Nimble has two basic interface [NimbleView](https://github.com/lenguyenthanh/nimble/blob/master/nimble-core/src/main/java/com/lenguyenthanh/nimble/NimbleView.java) and [NimblePresenter](https://github.com/lenguyenthanh/nimble/blob/master/nimble-core/src/main/java/com/lenguyenthanh/nimble/NimblePresenter.java). They are base interfaces for ant views and presenters.

You should follow these steps to create a Mvp component in your application:
1.  Create a View interface which extents NimbleView:
```java
    public interface MainView extends NimbleView {
        void showUser(User user);
    }
```
2.  Create a Presenter interface which extents NimblePresenter with View generic like:
```java
    public interface MainPresenter extends NimblePresenter<MainView> {
        void getUser();
    }
```
3.  Implement your Presenter which should extents [BasePresenter](https://github.com/lenguyenthanh/nimble/blob/master/nimble-core/src/main/java/com/lenguyenthanh/nimble/BasePresenter.java):
```java
    public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {
        public void getUser(){
            User user = ...
            getView().showUser(user);
        }
```
4. Implement your View which should extents base Views like NimbleActivity:
```java
    public class MainActivity extends NimbleActivity<MainView> {

    @Override
    protected MainPresenter presenter() {
        return presenter;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter().getUser();
    }
}
```

Done you have an Mvp component now.

### Inspiration

Nimble has a lot of inspiration from other great MVP libraries for Android:

* Mortar - https://github.com/square/mortar
* Nucleus - https://github.com/konmik/nucleus
* Mosby - https://github.com/sockeqwe/mosby

### Version
0.5.0

## Installation
Just clone and copy/paste. I am trying to publish it to Jcenter() and maven() center. So sorry for any inconvenient. Will be update is soon.

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