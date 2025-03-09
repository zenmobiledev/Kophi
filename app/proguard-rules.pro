-keep class retrofit2.** { *; }
-keep class com.squareup.okhttp3.** { *; }
-keep interface retrofit2.** { *; }

-keep class com.mobbelldev.kophi.data.source.remote.model** { *; }
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

-keep class * implements dagger.hilt.InstallIn
-if class * extends android.app.Application
-keep @dagger.hilt.android.HiltAndroidApp class <1> { *; }