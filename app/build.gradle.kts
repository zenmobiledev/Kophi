plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.ksp)
//    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.example.kophi"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.kophi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
    }
}

dependencies {
    // splash screen
    implementation(libs.androidx.core.splashscreen)

    // View Pager 2
    implementation(libs.androidx.viewpager2)

    // Data Store - Preference
    implementation(libs.androidx.datastore.preferences)

    // Hilt
//    implementation(libs.hilt.android)
//    ksp(libs.hilt.android.compiler)

    // Coil
    implementation("io.coil-kt.coil3:coil:3.1.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.1.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}