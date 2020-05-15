# Android MVVM Architecture for Enterprise Mobile Application using Architectural Components

# Highlights

1. MVVM Architectural pattern
2. Offline Support using Retrofit
3. Pull to Refresh
4. Pagination



The application has been built with **offline support**. It has been designed using **Android Architecture components** with **Retrofit** for offline data caching.

The whole application is built based on the MVVM architectural pattern.

# Application Architecture
<img src="/screenshots/arch.png"  alt="Home"/>

The main advatage of using MVVM, there is no two way dependency between ViewModel and Model unlike MVP. Here the view can observe the datachanges in the viewmodel as we are using LiveData which is lifecycle aware. The viewmodel to view communication is achieved through observer pattern (basically observing the state changes of the data in the viewmodel).


# Programming Practices Followed
a) Android Architectural Components <br/>
b) Dagger 2 for Dependency Injection <br/>
c) MVVM <br/>
d) Retrofit with Okhttp <br/>

# How to build ?

Open terminal and type the below command to generate debug build <br/>

``` ./gradlew assembleDebug ```

# How to update Google Maps Key

Open AndroidManifest.xml, add your google map key in the below section.

``` <meta-data
                android:name="com.google.android.maps.v2.API_KEY"
                android:value=""/>
                
                ```
