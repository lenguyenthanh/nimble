# Nimble [![Build Status](https://travis-ci.org/lenguyenthanh/nimble.svg?branch=master)](https://travis-ci.org/lenguyenthanh/nimble) [![codecov.io](https://codecov.io/github/lenguyenthanh/nimble/coverage.svg?branch=master)](https://codecov.io/github/lenguyenthanh/nimble?branch=master)

Nimble is a small, quick and flexible Model-View-Presenter library for Android

### Why Nimble
* It has yet 500 lines of code. So it is easy to understand, and make it your own.
* Well tested code.
* It is designed to be used with Dagger but can use dependently.
* It is designed to be flexible Mvp like: Activity, ViewGroup, Fragment
* It can be use as multiple MVP in one screen as well as, one Mvp for multiple screens
* It provide mechanism to save data when activity is recreated throws bundle

### Usage

``` java
    View
    Presenter
```

### Inspiration

Nimble has alot of inspiration from other MVP library for Android:

* Mortar - https://github.com/square/mortar
* Mosby - https://github.com/sockeqwe/mosby
* Nucleus - https://github.com/konmik/nucleus

### Version
0.5.0

## Installation

``` gradle
    compile 'com.lenguyenthanh.nimble:0.5.0'
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