Android Kotlin Clean Architecture & Components Example
===========================================================

This is a sample app & basic code that uses Clean Architecture Components.

**NOTE** It is a relatively more complex and complete example so if you are not familiar
with [Architecture Components][arch], you are highly recommended to check other examples
in this repository first.

Introduction
-------------

### Structure
The app is composed of 3 modules 
![Structure](images/clean_architecture_reloaded_layers.png)

#### Domain Layer

#### Data Layer

#### Presentation Layer

### Architecture
#### MVVM 

### Building
You can open the project in Android studio and press run.
### Testing
The project uses both instrumentation tests that run on the device
and local unit tests that run on your computer.
To run both of them and generate a coverage report, you can run:

`./gradlew fullCoverageReport` (requires a connected device or an emulator)

#### Domain layer Tests
##### UseCase Tests

#### Data Layer Unit Tests
##### Model Mapper Tests

##### Repository Tests

##### Webservice Tests
The project uses [MockWebServer][mockwebserver] project to test REST api interactions.

#### Presentation layer Tests
##### ViewModel Tests

##### Model Mapper Tests

### Libraries
* [Android Support Library][support-lib]
* [Android Architecture Components][arch]
* [Android Data Binding][data-binding]
* [Dagger 2][dagger2] for dependency injection
* [Rx Android 2][rxandroid2] for reactive extensions for Android
* [Rx Java 2][rxjava2] for reactive extensions for the JVM
* [Retrofit][retrofit] for REST api communication
* [Glide][glide] for image loading
* [Timber][timber] for logging
* [espresso][espresso] for UI tests
* [mockito][mockito] for mocking in tests


[mockwebserver]: https://github.com/square/okhttp/tree/master/mockwebserver
[support-lib]: https://developer.android.com/topic/libraries/support-library/index.html
[arch]: https://developer.android.com/arch
[data-binding]: https://developer.android.com/topic/libraries/data-binding/index.html
[espresso]: https://google.github.io/android-testing-support-library/docs/espresso/
[dagger2]: https://google.github.io/dagger
[rxandroid2]: https://github.com/ReactiveX/RxAndroid
[rxjava2]: https://github.com/ReactiveX/RxJava
[retrofit]: http://square.github.io/retrofit
[glide]: https://github.com/bumptech/glide
[timber]: https://github.com/JakeWharton/timber
[mockito]: http://site.mockito.org

License
--------

Copyright 2017 The Android Open Source Project, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.
