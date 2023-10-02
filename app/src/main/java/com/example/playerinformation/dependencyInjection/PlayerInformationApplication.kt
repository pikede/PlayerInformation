package com.example.playerinformation.dependencyInjection

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PlayerInformationApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PlayerInformationApplication)
            modules(AppModules)
        }
    }
}