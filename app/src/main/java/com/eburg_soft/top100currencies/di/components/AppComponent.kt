package com.eburg_soft.top100currencies.di.components

import com.eburg_soft.top100currencies.MainActivity
import com.eburg_soft.top100currencies.di.modules.AppModule
import com.eburg_soft.top100currencies.di.modules.ChartModule
import com.eburg_soft.top100currencies.di.modules.MvpModule
import com.eburg_soft.top100currencies.di.modules.RestModule
import com.eburg_soft.top100currencies.mvp.presenter.CurrenciesPresenter
import com.eburg_soft.top100currencies.mvp.presenter.LatestChartPresenter
import com.eburg_soft.top100currencies.ui.LatestChart
import com.eburg_soft.top100currencies.ui.activities.ChartActivity
import com.eburg_soft.top100currencies.ui.fragments.CurrenciesListFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RestModule::class, MvpModule::class, ChartModule::class))
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(activity: ChartActivity)

    fun inject(fragment: CurrenciesListFragment)

    fun inject(presenter: CurrenciesPresenter)
    fun inject(presenter: LatestChartPresenter)

    fun inject(chart: LatestChart)
}