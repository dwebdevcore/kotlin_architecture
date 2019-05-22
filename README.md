# Android Architecture Kotlin + Dagger2 + Room + LiveData + Retrofit

Project contributors: dmobiledevcore
### Summary
This sample stands on the principles of [Android Architecture].

It's based on the MVC sample, splitting the application in four layers:

<img src="https://developer.android.com/topic/libraries/architecture/images/final-architecture.png" alt="Diagram"/>


### Layers responsibilities
#### UI Controller (Activities, Fragments & Custom views)

 - Activities & Fragments
 - Observes the ViewModel
 - Keeps the UI up-to-date
 - Forwards user Actions back to the ViewModel
 
#### ViewModel
  
  - Prepares & keeps data for the UI
  - Includes LiveData, Observables etc.
  - Survives configuration changes
  - The gateway for the UI Controller
  
#### Repository
 
  - The complete data model from the App
  - Provides simple data modification & retrieval APIs

#### Data Source
  - Provides local or network sources for data

### Maintainability

ViewModel concept makes application more flexible for support 


[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Android--CleanArchitecture-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/5775)
