import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.google.firebase.crashlytics)
}

android {
    namespace = "com.mobbelldev.kophi"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mobbelldev.kophi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) localPropertiesFile.inputStream().use {
            localProperties.load(it)
        }

        buildConfigField(
            type = "String",
            name = "DEFAULT_WEB_CLIENT_ID",
            value = localProperties["DEFAULTWEBCLIENTID"].toString()
        )
        buildConfigField(
            type = "String",
            name = "X_SECRET_APP",
            value = localProperties["XSECRETAPP"].toString()
        )
        buildConfigField(
            type = "String",
            name = "SNAP_URL",
            value = localProperties["SNAPURL"].toString()
        )
    }

    signingConfigs {
        create("release") {
            storeFile = file(rootProject.file(properties["KEYSTORE_FILE"] ?: ""))
            storePassword = properties["KEYSTORE_PASSWORD"] as String?
            keyAlias = properties["KEY_ALIAS"] as String?
            keyPassword = properties["KEY_PASSWORD"] as String?
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    // splash screen
    implementation(libs.androidx.core.splashscreen)

    // View Pager 2
    implementation(libs.androidx.viewpager2)

    // Coil
    implementation(libs.coil)
    implementation(libs.coil.network.okhttp)

    // Shimmer
    implementation(libs.shimmer)

    // Swipe Refresh
    implementation(libs.androidx.swiperefreshlayout)

    // Data Store - Preference
    implementation(libs.androidx.datastore.preferences)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.crashlytics)

    // Credential
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Room
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    // AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.activity)

    // Unit Testing and UI Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}