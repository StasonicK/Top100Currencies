package com.eburg_soft.top100currencies.di.component

import com.eburg_soft.top100currencies.chart.LatestChart
import com.eburg_soft.top100currencies.di.module.AppModule
import com.eburg_soft.top100currencies.di.module.ChartModule
import com.eburg_soft.top100currencies.di.module.MvpModule
import com.eburg_soft.top100currencies.di.module.RestModule
import com.eburg_soft.top100currencies.mvp.presenter.CurrenciesPresenter
import com.eburg_soft.top100currencies.ui.activities.AboutActivity
import com.eburg_soft.top100currencies.ui.activities.ChartActivity
import com.eburg_soft.top100currencies.ui.activities.MainActivity
import com.eburg_soft.top100currencies.ui.fragments.CurrenciesListFragment
import com.eburg_soft.topcrypts.mvp.presenter.LatestChartPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RestModule::class, MvpModule::class, ChartModule::class))
@Singleton
interface AppComponent {

    fun inject(aboutActivity: AboutActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(activity: ChartActivity)

    fun inject(fragment: CurrenciesListFragment)
    fun inject(latestChart: LatestChart)

    fun inject(presenter: CurrenciesPresenter)
    fun inject(presenter: LatestChartPresenter)
}