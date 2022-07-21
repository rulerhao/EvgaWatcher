![titleimage](https://github.com/rulerhao/EvgaWatcher/blob/master/images/TitleImage.png)

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://github.com/rulerhao/EvgaWatcher/blob/master/badges/Apache%202.0.svg"/></a>
  <a href="https://developer.android.com/studio/releases/platforms#5.0"><img alt="API" src="https://github.com/rulerhao/EvgaWatcher/blob/master/badges/API21%2B.svg"/></a>
  <a href="https://github.com/rulerhao/EvgaWatcher/actions"><img alt="Build Status" src="https://github.com/rulerhao/EvgaWatcher/blob/master/badges/Android%20CI.svg"/></a> 
  <a href="https://github.com/rulerhao"><img alt="Github" src="https://github.com/rulerhao/EvgaWatcher/blob/master/badges/GitHub-rulerhao.svg"/></a> 
</p>

<p align="center">
  Observer for EVGA products lovers. Notify you when the price and stock availability are updated.
</p>

<p align="center">
  <a href="https://play.google.com/store/apps/details?id=com.rulhouse.evgawatcher">
    <img alt="Google play" src="https://github.com/rulerhao/EvgaWatcher/blob/master/icon/google-play-download-icon-27.jpg" width=200px/>
  </a>
</p>

## :evergreen_tree: Purpose
- Implementing Jetpack Compose for entire UI.
- Implementation of Android app with Jetpack libraries like work manager.
- Doing background tasks with Coroutines, flow and livedata.
- Following clean architecture principles.
- Adopting android ci as the ci/cd tools for checking build and unit test passable.

## :light_rail: Demonstrate
<p align="center">
  <a href="Demo"> <img width="300" src="https://github.com/rulerhao/EvgaWatcher/blob/master/screenshot/demo.gif" />
</p>
  
### All Products Screen
<p align="center">
<a href="CrawlerScreen1"> <img width="300" src="https://github.com/rulerhao/EvgaWatcher/blob/master/screenshot/CrawlerScreen1.png" />
<a href="CrawlerScreen2"> <img width="300" src="https://github.com/rulerhao/EvgaWatcher/blob/master/screenshot/CrawlerScreen2.png" />
<a href="CrawlerScreen3"> <img width="300" src="https://github.com/rulerhao/EvgaWatcher/blob/master/screenshot/CrawlerScreen3.png" />
</p>

### Favorite Products Screen
<p align="center">
<a href="FavoriteScreen1"> <img width="300" src="https://github.com/rulerhao/EvgaWatcher/blob/master/screenshot/FavoriteScreen1.png" />
<a href="FavoriteScreen2"> <img width="300" src="https://github.com/rulerhao/EvgaWatcher/blob/master/screenshot/FavoriteScreen2.png" />
<a href="FavoriteScreen3"> <img width="300" src="https://github.com/rulerhao/EvgaWatcher/blob/master/screenshot/FavoriteScreen3.png" />
</p>

### Others Screens
<p align="center">
<a href="ProducteScreen"> <img width="300" src="https://github.com/rulerhao/EvgaWatcher/blob/master/screenshot/ProductScreen.png" />
<a href="ReminderScreen"> <img width="300" src="https://github.com/rulerhao/EvgaWatcher/blob/master/screenshot/ReminderScreen1.png" />
<a href="NotificationScreen"> <img width="300" src="https://github.com/rulerhao/EvgaWatcher/blob/master/screenshot/NotificationScreen.png" />
</p>

  
## :bulb: Tech stack
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) + [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) for asynchronous.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
- [Repository Pattern](https://developer.android.com/codelabs/basic-android-kotlin-training-repository-pattern#5)
- Jetpack
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) LiveData is lifecycle-aware and ensuresonly updates app component observers that are in an active lifecycle state.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
    - [Room](https://developer.android.com/jetpack/androidx/releases/room) - The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
    - [Compose](https://developer.android.com/jetpack/compose) - Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android.
    - [Data Store](https://developer.android.com/topic/libraries/architecture/datastore) - A data storage solution that allows you to store key-value pairs or typed objects with [protocol buffers](https://developers.google.com/protocol-buffers) and uses Kotlin coroutines and Flow to store data asynchronously, consistently, and transactionally.
    - [Work Manager](https://developer.android.com/topic/libraries/architecture/workmanager) - The persistent working solution for android after background task restriction becomes harder.
- Architecture
    -   MVVM Architecture (View - ViewModel - Model)
- [Glide](https://github.com/bumptech/glide) - Loading images from network.
- [Material Design 3](https://m3.material.io/) - The latest version of Material Design includes personalization and accessibility features that put people at the center.
- [Jsoup](https://jsoup.org/) - A Java library that implements the [WHATWG HTML5](https://whatwg.org/html) specification and parses HTML to get information.
- Unit Test
    - Junit 4 - Unit testing framework for the Java.
  
## :blue_book: Data
The information of gpu products is crawling from [Evga](https://tw.evga.com/products/productlist.aspx?type=0).
  
## MAD Score
![MAD Score](https://github.com/rulerhao/EvgaWatcher/blob/master/mad%20score/summary.png)
  
## :pencil: License
```xml
Copyright 2022 RulerHao(Jia-Hao Tsao) Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
