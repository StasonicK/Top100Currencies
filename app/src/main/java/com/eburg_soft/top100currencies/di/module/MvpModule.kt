package com.eburg_soft.top100currencies.di.module

import com.eburg_soft.top100currencies.mvp.presenter.CurrenciesPresenter
import com.eburg_soft.topcrypts.mvp.presenter.LatestChartPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MvpModule {

    @Provides
    @Singleton
    fun provideCurrenciesPresenter(): CurrenciesPresenter = CurrenciesPresenter()

    @Provides
    @Singleton
    fun provideLatestChartPresenter(): LatestChartPresenter = LatestChartPresenter()
}