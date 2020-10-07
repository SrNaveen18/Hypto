package com.example.hypto

import android.app.Application
import com.example.hypto.di.networkModule
import com.example.hypto.di.repositoryModule
import com.example.hypto.di.viewModelModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class HyptoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            loadKoinModules(listOf(viewModelModule,networkModule,repositoryModule))
        }
    }
}