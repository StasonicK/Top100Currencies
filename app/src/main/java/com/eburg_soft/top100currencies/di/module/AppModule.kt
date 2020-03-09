package com.eburg_soft.top100currencies.di.module

import android.content.Context
import com.eburg_soft.top100currencies.common.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule (private val app: App) {

    @Provides
    @Singleton
    fun provideContext(): Context = app
}