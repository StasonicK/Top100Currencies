package com.eburg_soft.top100currencies.di.module

import com.eburg_soft.top100currencies.data.chart.LatestChart
import com.eburg_soft.top100currencies.common.extension.YearValueFormatter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ChartModule {

    @Provides
    @Singleton
    fun provideLatestChart() = LatestChart()

    @Provides
    @Singleton
    fun provideYearFormatter() = YearValueFormatter()
}