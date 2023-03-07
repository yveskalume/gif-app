<h1 align="center">GifApp</h1><br>
<p align="center">  
GifApp is an Android application built using jetpack compose and other modern Android technology stacks and the MVVM architecture. 
  The application just shows Gif images from <a href="https://developers.giphy.com">Giphy</a> API rest
</p>
<br>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=24"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
</p>

## Download
Go to the [Releases](https://github.com/eric-ampire/lottiefiles-app/releases) to download the latest APK.

<!-- ## Screenshots
<p align="center">
  <img src="/preview/preview_01.gif" width="32%"/>
  <img src="/preview/preview_02.gif" width="32%"/>
  <img src="/preview/preview_03.gif" width="32%"/>
</p> -->

## Tech stack & Open-source libraries
- Minimum SDK level 24
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Hilt for dependency injection.
- JetPack
    - Compose - A modern toolkit for building native Android UI.
    - Coroutines Flow - notify domain layer data to views.
    - Lifecycle - dispose observing data when lifecycle state changes.
    - ViewModel - UI related data holder, lifecycle aware.
    - Room Persistence - construct database.
- Material Design
- [Lottie Android](https://airbnb.design/lottie/) - Lottie is a library that renders After Effects animations in real time, allowing apps to use animations as easily as they use static images.
- [Ktor](https://github.com/square/retrofit) - construct the REST APIs and paging network data.

# License

```
Designed and developed by 2020 ericampire (Eric Ampire)

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
