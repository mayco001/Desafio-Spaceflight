package com.mayco.desafiospaceflight

import android.app.Application
import android.content.Context
import com.mayco.desafiospaceflight.di.applicationModule
import com.mayco.desafiospaceflight.di.repositoruModule
import com.mayco.desafiospaceflight.di.viewModelModule
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NewsAplication : Application() {
    override fun onCreate() {
        super.onCreate()

        instace = applicationContext

        setupHawk()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@NewsAplication)
            androidFileProperties()
            modules(applicationModule, viewModelModule, repositoruModule)
        }
    }

    private fun setupHawk() = Hawk.init(this).build()

    companion object {
        lateinit var instace: Context
    }
}