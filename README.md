## Technology Used / Tech Stack

[![JDK](https://img.shields.io/badge/openjdk-21.0.3-437291?style=for-the-badge&logo=openJdk&logoColor=white)](https://openjdk.org/)
[![Android Studio](https://img.shields.io/badge/Android_Studio-2024.2.1_Patch_3-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white)](https://developer.android.com/studio)
![Android Gradle Plugin](https://img.shields.io/badge/Android_Gradle_Plugin-8.7.3-3DDC84?style=for-the-badge&logo=android&logoColor=white)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.0-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](http://kotlinlang.org)
[![KSP](https://img.shields.io/badge/KSP-2.0.0--1.0.22-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://github.com/google/ksp)
[![Gradle](https://img.shields.io/badge/gradle-8.9-02303A?style=for-the-badge&logo=gradle&logoColor=white)](https://developer.android.com/studio/releases/gradle-plugin)
[![Hilt](https://img.shields.io/badge/hilt-2.51.1-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/training/dependency-injection/hilt-android)
[![Navigation](https://img.shields.io/badge/Navigation-2.8.6-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/jetpack/androidx/releases/navigation)
[![lifecycle](https://img.shields.io/badge/Lifecycle-2.8.7-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/jetpack/androidx/releases/lifecycle)
[![retrofit](https://img.shields.io/badge/Retrofit-2.11.0-000000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/square/retrofit)
[![Gson Converter](https://img.shields.io/badge/Converter_Gson-2.11.0-000000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/square/retrofit/blob/trunk/retrofit-converters/gson/README.md)
[![Logging Interceptor](https://img.shields.io/badge/Logging_Interceptor-4.12.0-000000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
[![Data Store](https://img.shields.io/badge/Data_Store_Preference-1.1.2-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/jetpack/androidx/releases/datastore)
[![Room Database](https://img.shields.io/badge/Room_Database-2.6.1-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/jetpack/androidx/releases/room)
[![Splash Screen](https://img.shields.io/badge/Splash_Screen-1.0.0-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/develop/ui/views/launch/splash-screen)
[![Coil](https://img.shields.io/badge/Coil-3.1.0-000000?style=for-the-badge&logo=github&logoColor=white)](https://coil-kt.github.io/coil/)
[![Shimmer Android](https://img.shields.io/badge/Shimmer_Android-0.5.0-0467DF?style=for-the-badge&logo=meta&logoColor=white)](https://github.com/facebookarchive/shimmer-android)
[![Swipe Refresh Layout](https://img.shields.io/badge/Swipe_Refresh_Layout-1.1.0-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout)
[![View Pager 2](https://img.shields.io/badge/View_Pager_2-1.1.0-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/jetpack/androidx/releases/viewpager2)
[![Firebase](https://img.shields.io/badge/firebase-ffca28?style=for-the-badge&logo=firebase&logoColor=black)](https://firebase.google.com/)

# KOPHI

**KOPHI** adalah aplikasi untuk pecinta kopi yang memudahkan pengguna menemukan kopi berdasarkan
kategori dan memesan kopi dengan preferensi pengguna.

Aplikasi **KOPHI** menyediakan sistem pembayaran yang fleksibel, mendukung berbagai metode transaksi
termasuk debit maupun kredit.

## Table of Contents

- [Technology Used / Tech Stack](#technology-used--tech-stack)
- [Features](#features)
- [Installation (How to run the project)](#installation-how-to-run-the-project)
- [Tree / Folder Structure](#tree--folder-structure)
- [Architecture](#architecture)
- [Design Pattern](#design-pattern)
- [Todos](#todos)
- [Done](#done)
- [Bugs](#bugs)
- [Flowchart](#flowchart)
- [Preview](#preview)
- [Demo](#demo)
- [Credit / Contributor(s)](#credit--contributors)

## Features

> - **Preferensi**: Pengguna bisa menyesuaikan pemesanan kopi dimulai dari penyajian, jenis susu dan
    tingkat kemanisan.
>- **Pembayaran**: Pengguna bisa membayar tagihan dengan berbagai metode pembayaran yang tersedia.
>- **Transaksi**: Pengguna bisa melihat daftar transaksi dari berbagai status(*Expired*,
   *Cancelled*, *Paid* dan *Pending*), pengguna juga bisa melanjutkan pembayaran apabila status
   tersebut masih pending atau pengguna juga bisa membatalkan pesanan tersebut.
>- **Pengaturan**: Pengguna bisa mengaktifkan Mode Gelap ataupun mengubah bahasa.

## Installation (How to run the project)

To run the project locally, follow these steps:

### 1. Clone the repository

> - ```https://github.com/zenmobiledev/kophi.git```
>- ```cd kophi```

### 2. Open the project

> - Launch your preferred Integrated Development Environment (IDE), such as Android Studio or
    IntelliJ IDEA. Then, open the ```kophi``` project directory within the IDE.

### 3. Build the project

Ensure that all necessary dependencies are installed. In Android Studio or IntelliJ IDEA, you can
typically do this by:

> - **Syncing the Project**: The IDE should automatically prompt you to sync the project with the
    Gradle files. If not, you can manually sync by clicking on the "Sync Project with Gradle Files"
    button.
>- **Building the Project:** Navigate to the ```Build``` menu and select ```Build Project```. This
   process will compile the code and prepare the application for running.

### 4. Run the application

After the build process completes successfully:

> - **Select a Device**: Choose an emulator or a physical device connected to your computer where
    you want to run the application.

> - **Launch the App**: Click on the green 'Run' button (usually depicted as a play icon) in the IDE
    toolbar, or navigate to ```Run``` > ```Run 'app'```. This action will install and start the
    application on the selected device.

## Tree / Folder Structure

```
.
└── app
    └── src
        └── main
            └── java
                └── com
                    └── mobbelldev
                        └── kophi
                            ├── data
                            │   ├── mapper
                            │   │   └── Mapper.kt
                            │   ├── repository
                            │   │   └── CoffeeRepositoryImpl.kt
                            │   └── source
                            │       ├── local
                            │       │   ├── dao
                            │       │   │   └── CoffeeCartDao.kt
                            │       │   ├── database
                            │       │   │   └── AppDatabase.kt
                            │       │   ├── datasource
                            │       │   │   ├── CoffeeLocalDataSource.kt
                            │       │   │   └── CoffeeLocalDataSourceImpl.kt
                            │       │   ├── entity
                            │       │   │   └── CoffeeCartEntity.kt
                            │       │   └── preference
                            │       │       ├── PreferenceDataStore.kt
                            │       │       └── PreferenceParameter.kt
                            │       └── remote
                            │           ├── api
                            │           │   └── CoffeeService.kt
                            │           ├── datasource
                            │           │   ├── CoffeeRemoteDataSource.kt
                            │           │   └── CoffeeRemoteDataSourceImpl.kt
                            │           └── model
                            │               ├── request
                            │               │   ├── ContinueWithGoogleRequest.kt
                            │               │   └── OrderRequest.kt
                            │               └── response
                            │                   ├── AuthenticationResponse.kt
                            │                   ├── CancelOrderResponse.kt
                            │                   ├── CoffeeResponse.kt
                            │                   ├── OrderSnapResponse.kt
                            │                   └── OrdersResponse.kt
                            ├── di
                            │   ├── AppModule.kt
                            │   ├── LocalModule.kt
                            │   └── NetworkModule.kt
                            ├── domain
                            │   ├── interactor
                            │   │   └── CheckoutInteractor.kt
                            │   ├── model
                            │   │   ├── Authentication.kt
                            │   │   ├── CancelOrder.kt
                            │   │   ├── Coffee.kt
                            │   │   ├── CoffeeCart.kt
                            │   │   ├── ContinueWithGoogle.kt
                            │   │   ├── Order.kt
                            │   │   ├── OrderSnap.kt
                            │   │   └── Orders.kt
                            │   ├── repositories
                            │   │   └── CoffeeRepository.kt
                            │   └── usecase
                            │       ├── AuthenticationUseCase.kt
                            │       ├── CancelOrderUseCase.kt
                            │       ├── CheckoutUseCase.kt
                            │       ├── CoffeeUseCase.kt
                            │       ├── DecrementQuantityUseCase.kt
                            │       ├── DeleteAllOrdersUseCase.kt
                            │       ├── DeleteCoffeeCartUseCase.kt
                            │       ├── GetAllCartCoffeesUseCase.kt
                            │       ├── GetDarkModeUseCase.kt
                            │       ├── GetEmailUseCase.kt
                            │       ├── GetLanguageUseCase.kt
                            │       ├── GetOnBoardingUseCase.kt
                            │       ├── GetOrdersUseCase.kt
                            │       ├── GetTokenUseCase.kt
                            │       ├── GetUserIdUseCase.kt
                            │       ├── IncrementQuantityUseCase.kt
                            │       ├── InsertCoffeeCartUseCase.kt
                            │       ├── LogoutUseCase.kt
                            │       ├── SaveTokenUseCase.kt
                            │       ├── SaveUserIdUseCase.kt
                            │       ├── SetDarkModeUseCase.kt
                            │       ├── SetEmailUseCase.kt
                            │       ├── SetLanguageUseCase.kt
                            │       ├── SetOnBoardingUseCase.kt
                            │       └── UpdateQuantityAndSubtotalUseCase.kt
                            ├── presentation
                            │   └── ui
                            │       ├── authentication
                            │       │   ├── AuthenticationActivity.kt
                            │       │   └── AuthenticationViewModel.kt
                            │       ├── coffee
                            │       │   ├── adapter
                            │       │   │   └── CoffeeAdapter.kt
                            │       │   ├── ads
                            │       │   │   ├── adapter
                            │       │   │   │   └── AdsAdapter.kt
                            │       │   │   └── AdsActivity.kt
                            │       │   ├── checkout
                            │       │   │   ├── adapter
                            │       │   │   │   ├── AdapterCallback.kt
                            │       │   │   │   └── CheckoutAdapter.kt
                            │       │   │   ├── CheckoutActivity.kt
                            │       │   │   └── CheckoutViewModel.kt
                            │       │   ├── detail
                            │       │   │   └── CoffeeDetailActivity.kt
                            │       │   ├── payment
                            │       │   │   └── PaymentActivity.kt
                            │       │   ├── CoffeeFragment.kt
                            │       │   └── CoffeeViewModel.kt
                            │       ├── main
                            │       │   └── MainActivity.kt
                            │       ├── onboarding
                            │       │   ├── adapter
                            │       │   │   └── AppIntroViewPager2Adapter.kt
                            │       │   ├── AppIntroActivity.kt
                            │       │   └── AppIntroViewModel.kt
                            │       ├── profile
                            │       │   ├── language
                            │       │   │   ├── LanguageActivity.kt
                            │       │   │   ├── LanguageViewModel.kt
                            │       │   │   └── Languages.kt
                            │       │   ├── ProfileFragment.kt
                            │       │   └── ProfileViewModel.kt
                            │       └── transaction
                            │           ├── adapter
                            │           │   ├── ItemDetailTransactionAdapter.kt
                            │           │   ├── ItemTransactionAdapter.kt
                            │           │   └── OnItemClickListener.kt
                            │           ├── StatusPayment.kt
                            │           ├── TransactionFragment.kt
                            │           └── TransactionViewModel.kt
                            ├── utils
                            │   ├── CapitalizeFirst.kt
                            │   ├── ConvertDateTime.kt
                            │   ├── IDRCurrency.kt
                            │   └── ResultResponse.kt
                            └── MyApp.kt
```

## Architecture

| Architecture                              |
|-------------------------------------------|
| ![Architecture](assets/architecture.webp) |

## Design Pattern

*[Design Pattern](https://www.kodeco.com/18409174-common-design-patterns-and-app-architectures-for-android#toc-anchor-001)
**
>- Clean Architecture Pattern: Data Layer, Domain Layer, Presentation Layer
>- Repository Pattern
>- Dependency Injection Pattern
>- Use Case Pattern
>- MVVM (Model-View-ViewModel) Pattern
>- Adapter Pattern
>- Data Source Pattern
>- DAO (Data Access Object) Patern
>- Factory Pattern
>- Observer Pattern
>- Mapper Pattern
>- Singleton Pattern

## Todos

>- [ ] Unit Testing

## Done

**Point Penilaian**:
>- [x] Local Storage CRUD
>- [x] API Integration
>- [x] Payment Integration
>- [x] State Management
>- [x] Data Preferences
>- [x] Alert Dialog
>- [x] Firebase Crashlytics
>- [x] Dependency Management
>- [x] Firebase Authentication
>- [x] Code Obfuscation
>- [x] Code Quality
>- [x] App Distribution

**Fitur Aplikasi**:
>- [x]  Preferensi
>- [x]  Pembayaran
>- [x]  Transaksi
>- [x]  Pengaturan

## Bugs

> - [ ] Network Handling

## Flowchart

:exclamation: **COMING SOON**

## Preview

| Splash Screen                              | On Boarding / Walkthrough App                        | Authentication                               |
|--------------------------------------------|------------------------------------------------------|----------------------------------------------|
| ![Splash Screen](assets/splash_screen.png) | ![On Boarding / Walkthrough](assets/on_boarding.png) | ![Authentication](assets/authentication.png) |

| Coffee Page 1                            | Coffee Detail Page                                   | Coffee Page 2                              |
|------------------------------------------|------------------------------------------------------|--------------------------------------------|
| ![Coffee Page 1](assets/coffee_page.png) | ![Coffee Detail Page](assets/coffee_detail_page.png) | ![Coffee Page 2](assets/coffee_page_2.png) |

| Checkout Page                              | Payment Page                             | Transaction Page                                 |
|--------------------------------------------|------------------------------------------|--------------------------------------------------|
| ![Checkout Page](assets/checkout_page.png) | ![Payment Page](assets/payment_page.png) | ![Transaction Page](assets/transaction_page.png) |

| Profile Page                             | 
|------------------------------------------|
| ![Profile Page](assets/profile_page.png) | 

## Demo

:exclamation: **COMING SOON**

## Credit / Contributor(s)

- [Zaenal Arif](https://github.com/zenmobiledev)

