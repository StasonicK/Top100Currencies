package com.eburg_soft.topcrypts.mvp.presenter

import com.eburg_soft.top100currencies.common.App
import com.eburg_soft.top100currencies.network.CoinGeckoApi
import com.eburg_soft.topcrypts.mvp.contract.LatestChartContract
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LatestChartPresenter : LatestChartContract.Presenter() {

    @Inject
    lateinit var geckoApi: CoinGeckoApi

    init {
        App.appComponent.inject(this)
    }

    override fun makeChart(id: String) {

        subscribe(geckoApi.getCoinMarketChart(id)
            .subscribeOn(Schedulers.io())
            .map { it.prices }
            .flatMap { Observable.fromIterable(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                view.hideProgress()
            }
            .subscribe({
                view.hideProgress()
                view.addEntryToChart(it[0], it[1])

            }, {
                view.hideProgress()
                view.showErrorMessage(it.message)

//                view.hideProgress()

                it.printStackTrace()
            })
        )
    }

    override fun refreshChart() {
        view.refresh()
    }
}
