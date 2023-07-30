package com.example.swipedemo

import android.app.Application
import com.example.swipedemo.utils.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SwipeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SwipeApplication)
            modules(networkModule)
        }
    }
}