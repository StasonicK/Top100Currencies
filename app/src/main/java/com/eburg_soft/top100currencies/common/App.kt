package com.eburg_soft.top100currencies.common

import android.app.Application
import com.eburg_soft.top100currencies.di.components.AppComponent
import com.eburg_soft.top100currencies.di.components.DaggerAppComponent
import com.eburg_soft.top100currencies.di.modules.AppModule
import com.eburg_soft.top100currencies.di.modules.ChartModule
import com.eburg_soft.top100currencies.di.modules.MvpModule
import com.eburg_soft.top100currencies.di.modules.RestModule

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .restModule(RestModule())
            .mvpModule(MvpModule())
            .chartModule(ChartModule())
            .build()
    }
}