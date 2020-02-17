package com.eburg_soft.top100currencies.mvp.presenter

import com.eburg_soft.top100currencies.common.App
import com.eburg_soft.top100currencies.formatThousands
import com.eburg_soft.top100currencies.model.network.CoinGeckoApi
import com.eburg_soft.top100currencies.mvp.contract.CurrenciesContract
import com.eburg_soft.top100currencies.ui.adapter.CurrenciesAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrenciesPresenter : CurrenciesContract.Presenter() {

    @Inject
    lateinit var geckoApi: CoinGeckoApi

    //initializing of the Dagger component
    init {
        App.appComponent.inject(this)
    }

    override fun makeList() {
        view.showProgress()

        subscribe(geckoApi.getCoinMarket()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { Observable.fromIterable(it) }
            .doOnNext {
                view.addCurrency(
                    CurrenciesAdapter.Currency(
                        it.id,
                        it.symbol,
                        it.name,
                        it.image,
                        it.current_price,
                        it.market_cap.formatThousands(),
                        it.market_cap_rank,
                        it.total_volume,
                        it.price_change_percentage_24h,
                        it.market_cap_change_percentage_24h,
                        it.circulating_supply,
                        it.total_supply,
                        it.ath,
                        it.ath_change_percentage
                    )
                )
            }
            .doOnComplete {
                view.hideProgress()
            }
            .subscribe({
                view.hideProgress()
                view.notifyAdapter()
            }, {
                view.showErrorMessage(it.message)
                view.hideProgress()
                it.printStackTrace()
            })
        )
    }

    override fun refreshList() {
        view.refresh()
        makeList()
    }
}