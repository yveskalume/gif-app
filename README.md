<h1 align="center">Gif App</h1><br>
<p align="center">  
Gif App is an Android application built using jetpack compose and other modern Android technology stacks and the MVVM architecture. 
  The application just shows animated Stickers from <a href="https://developers.giphy.com">Giphy's</a> Rest API
</p>
<br>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=24"><img alt="API" src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/yveskalume/gif-app/actions"><img alt="Build Status" src="https://github.com/yveskalume/gif-app/workflows/Android%20CI/badge.svg"/></a> <br>
</p>

## Screenshots
<p align="center">
  <img src="/preview/preview1.png" width="32%"/>
  <img src="/preview/preview2.png" width="32%"/>
  <img src="/preview/preview3.png" width="32%"/>
</p>

## Download
<a href="https://play.google.com/store/apps/details?id=com.yvkalume.gifapp">
<img src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png" height="70">
</a>

## Tech stack & Open-source libraries
- Minimum SDK level 24
- 100% [Kotlin](https://kotlinlang.org/) 
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) to simplify code that executes asynchronously.
- JetPack
    - [Compose](https://developer.android.com/jetpack/compose) - A modern toolkit for building native Android UI.
    - [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - for dependency injection.
    - [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) - dispose observing data when lifecycle state changes.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - UI related data holder, lifecycle aware.
    - [Room](https://developer.android.com/training/data-storage/room) - to construct database and persist data more easily.
    - [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - helps to load and display pages of data from a larger dataset in order to use both network bandwidth and system resources more efficiently
- [Material Design 3](https://developer.android.com/jetpack/compose/designsystems/material3) - Material 3 includes Material You personalization features like dynamic color, and is designed to be cohesive with the new visual style and system UI on Android 12 and above
- [Mavericks](https://airbnb.io/mavericks) - Mavericks is the Android MVI framework from Airbnb.
- [Lottie Android](https://github.com/airbnb/lottie-android) - Render After Effects animations natively on Android
- [Ktor](https://github.com/square/retrofit) - consume the REST APIs.
- [KSP](https://github.com/google/ksp) - Kotlin Symbol Processing API.
- [Accompanist Pager](https://google.github.io/accompanist/pager/) - A library which provides paging layouts for Jetpack Compose.
- [Coil](https://coil-kt.github.io/coil/compose/) - An image loading library for Android backed by Kotlin Coroutines.
- [JUnit](https://developer.android.com/training/testing/local-tests) - a “Unit Testing” framework
- [Mockk](https://mockk.io) - Mocking library for Kotlin
- [Chucker](https://github.com/ChuckerTeam/chucker) - An HTTP inspector for Android & OkHTTP
- [Secrets Gradle Plugin for Android](https://github.com/google/secrets-gradle-plugin) - A Gradle plugin for providing secrets to Android project.
- [Faker](https://serpro69.github.io/kotlin-faker/) - kotlin-faker is a data-generation library intended for use during development and testing

# Setup & Installation
Gif App is using the [Giphy Api](https://developers.giphy.com/docs/api/#quick-start-guide) for constructing RESTful API.
Create an account in order to have an api key and put it in `secrets.properties` file as following
```
giphyApiKey=Your Api Key
```

# Challenge and difficulties encountered
- Actually the goal is to keep things simple, that why i decided to not use (remove) the Jetpack Pagination,because this has somme constraints that will led me to rethinks some parts of my architecture.
I prefer to implement my own pagination logic in the future.

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/yveskalume/gif-app/stargazers)__ for this repository. :star:

# License

```
Designed and developed by 2020 yveskalume (Yves Kalume)

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
