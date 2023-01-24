package com.eyosiyas.contributors

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
* @HiltAndroidApp is a Hilt annotation that creates the necessary components for a Hilt-enabled application
* It automatically generates the necessary code to use Hilt for Dependency Injection in the Application class
* It also creates a custom Application class that extends Hilt's AndroidApplication class, which provides additional functionality for Hilt
* */
@HiltAndroidApp
class ContributorsApplication : Application()