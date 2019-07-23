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

# If your application seems to fail due to the optimization or obfuscation
# in release builds, you can verify that by selectively disabling any of the
# processing steps. You may then need to add some additional configuration
# (see manual). Debug builds already disable these steps by default.
-dontshrink
-dontoptimize
-ignorewarnings

#-optimizationpasses 5
#-dontusemixedcaseclassnames
#-dontskipnonpubliclibraryclasses
#-dontskipnonpubliclibraryclassmembers
#-dontpreverify
#-verbose
-dump class_files.txt
-printseeds seeds.txt
-printusage unused.txt
-printmapping mapping.txt
-printmapping out.map

########--------Android--------#########
-dontwarn android.hardware.**
-dontwarn android.support.v4.**

########--------Retrofit--------#########
-dontwarn okio.**
-dontwarn com.squareup.okhttp3.**
-dontwarn okhttp3.**
-dontwarn com.what3words.**
-dontwarn android.databinding.**
-dontwarn android.databinding.BindingAdapter.**
-dontwarn retrofit2.Platform$Java8
-dontwarn sun.misc.Unsafe
-dontwarn org.w3c.dom.bootstrap.DOMImplementationRegistry
-dontwarn com.google.**
-dontwarn org.apache.http.**
-dontwarn android.net.http.AndroidHttpClient
-dontwarn com.google.android.gms.**
-dontwarn com.airbnb.lottie.**
-dontwarn com.androidadvance.topsnackbar.**
-dontwarn com.gigamole.**
-dontwarn com.miguelcatalan.**
-dontwarn io.realm.**
-dontwarn com.google.code.**
-dontwarn com.google.dagger.**
-dontwarn com.squareup.retrofit2.**
-dontwarn io.reactivex.**
-dontwarn com.google.guava.**
-dontwarn me.tatarka.bindingcollectionadapter2.**
-dontwarn me.tatarka.bindingcollectionadapter.**

-keepattributes *Annotation*
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keep class android.databinding.**{*;}
-keep class com.google.guava.**{*;}
-keep class com.squareup.retrofit2.**{*;}
-keep class retrofit2.**{*;}
-keep class com.google.**{*;}
#-keep class org.apache.http.**{*;}
#-keep class com.google.android.gms.**{*;}
-keep class me.tatarka.bindingcollectionadapter2.**{*;}
-keep class me.tatarka.bindingcollectionadapter.**{*;}

-keep class com.bumptech.glide.**{*;}
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
