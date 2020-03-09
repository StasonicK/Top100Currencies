package com.eburg_soft.top100currencies.screens.currencies_list


import com.eburg_soft.top100currencies.screens.currencies_list.adapter.CurrenciesAdapter
import com.eburg_soft.top100currencies.common.App
import com.eburg_soft.top100currencies.network.CoinGeckoApi
import info.eburg_soft.top100currencies.formatThousands
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrenciesPresenter : CurrenciesContract.Presenter() {

    //внедряем источник данных
    @Inject
    lateinit var geckoApi: CoinGeckoApi

    //инициализируем компоненты Даггера
    init {
        App.appComponent.inject(this)

    }

    //создаем список, загружая данные с помощью RxJava
    override fun makeList() {
        view.showProgress()
        subscribe(geckoApi.getCoinMarket()
            .subscribeOn(Schedulers.io())
            .flatMap { Observable.fromIterable(it) }
            .observeOn(AndroidSchedulers.mainThread())
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

    //обновляем список
    override fun refreshList() {
        view.refresh()
        makeList()
    }
}