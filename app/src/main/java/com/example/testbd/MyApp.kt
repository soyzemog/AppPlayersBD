package com.example.testbd

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: Application() {
}

/**
 * agregar en el 'manifest'
 * - dentro de application:
 */