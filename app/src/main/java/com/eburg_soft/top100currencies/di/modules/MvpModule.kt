package com.eburg_soft.top100currencies.di.modules

import com.eburg_soft.top100currencies.mvp.presenter.CurrenciesPresenter
import com.eburg_soft.top100currencies.mvp.presenter.LatestChartPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MvpModule {

    @Provides
    @Singleton
    fun provideLatestChartPresenter(): LatestChartPresenter = LatestChartPresenter()

    @Provides
    @Singleton
    fun provideCurrenciesPresenter(): CurrenciesPresenter = CurrenciesPresenter()
}