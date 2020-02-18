package com.eburg_soft.top100currencies.common

import android.app.Application
import com.eburg_soft.top100currencies.di.module.AppModule
import com.eburg_soft.top100currencies.di.module.ChartModule
import com.eburg_soft.top100currencies.di.module.MvpModule
import com.eburg_soft.top100currencies.di.module.RestModule
import com.eburg_soft.top100currencies.di.component.AppComponent
import com.eburg_soft.top100currencies.di.component.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .restModule(RestModule())
            .mvpModule(MvpModule())
            .chartModule(ChartModule())
            .build()
    }
}