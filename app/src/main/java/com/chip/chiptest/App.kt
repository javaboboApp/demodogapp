package com.chip.chiptest

import android.app.Application
import com.chip.chiptest.di.apiModule
import com.chip.chiptest.di.repositoriesModule
import com.chip.chiptest.di.useCasesModule
import com.chip.chiptest.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    private val modules = listOf(
        apiModule,
        viewModelsModule,
        useCasesModule,
        repositoriesModule
    )

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(modules)
        }
    }
}