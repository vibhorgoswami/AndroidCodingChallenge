# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Obfuscate BuildConfig fields
-keepclassmembers class **.BuildConfig {
    public static final java.lang.String API_KEY;
}

# Keep the application entry point
-keep class com.example.otchallenge.MyApplication { *; }
-keep class com.example.otchallenge.MainActivity { *; }

# Keep Dagger-related classes
-keep class dagger.** { *; }
-keep class javax.inject.** { *; }
-keepclassmembers class * {
    @dagger.Provides <methods>;
    @dagger.Binds <methods>;
    @dagger.Module <methods>;
    @dagger.Component <methods>;
}

# Retrofit and GSON rules
# Retrofit interfaces
-keep interface retrofit2.** { *; }

# Keep serialized model classes
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Gson default keep rules
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-dontwarn sun.misc.**

# Keep Glide classes
-keep public class com.bumptech.glide.** { *; }
-dontwarn com.bumptech.glide.**

# Rules for Kotlin Coroutines
-dontwarn kotlinx.coroutines.**
-keepclassmembers class kotlinx.coroutines.** { *; }

# Keep all classes with @SerializedName annotation
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Keep fields annotated with @Expose
-keepclassmembers class * {
    @com.google.gson.annotations.Expose <fields>;
}

# Keep model classes
-keep class com.example.otchallenge.network.wrapper.** { *; }
-keep class com.example.otchallenge.models.** { *; }

# Keep the constructors of Gson model classes
-keepclassmembers class * {
    public <init>(...);
}

# Prevent ProGuard from stripping Gson classes
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

# Keep data classes
-keep class com.example.otchallenge.network.wrapper.NYTResponse { *; }
-keep class com.example.otchallenge.network.wrapper.BookDto { *; }
-keep class com.example.otchallenge.network.wrapper.Results { *; }

