<h1 align="center">Gif App</h1><br>
<p align="center">  
Gif App is an Android application built using jetpack compose and other modern Android technology stacks and the MVVM architecture. 
  The application just shows Gif images from <a href="https://developers.giphy.com">Giphy</a> API rest
</p>
<br>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=24"><img alt="API" src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/yveskalume/gif-app/actions"><img alt="Build Status" src="https://github.com/yveskalume/gif-app/workflows/Android%20CI/badge.svg"/></a> <br>
</p>

## Screenshots
<p align="center">
  <img src="/preview/preview1.gif" width="32%"/>
  <img src="/preview/preview2.gif" width="32%"/>
  <img src="/preview/preview3.gif" width="32%"/>
</p>

## Download
Go to the [Releases](https://github.com/yveskalume/gif-app/releases) to download the latest APK.

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
- [Material Design](https://developer.android.com/jetpack/compose/designsystems) - Material Design comes with a rich set of Material components
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
- In order to make the loading a bit smoother, I had to add pagination on the data coming from the local database, but this led me to rethink my architecture in order to keep things simple.
I had to remove the Mavericks viewmodels and I get the paginated data directly as viewmodel properties instead of going through a sealed class for example

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
