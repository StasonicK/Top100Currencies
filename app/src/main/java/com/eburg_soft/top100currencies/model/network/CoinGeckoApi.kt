package com.eburg_soft.top100currencies.model.network

import com.eburg_soft.top100currencies.model.network.responce.GeckoCoin
import com.eburg_soft.top100currencies.model.network.responce.GeckoCoinChart
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApi {

    //query a list of criptocoins
    @GET("coins/markets")
    fun getCoinMarket(
        @Query("vs_currency") vs: String = "usd",
        @Query("per_page") perPage: Int = 100,
        @Query("sparkline") sparkline: Boolean = false,
        @Query("order") order: String = "market_cap_desc"
    ): Observable<List<GeckoCoin>>

    //query data for a chart
    @GET("coins/{id}/market_chart")
    fun getCoinMarketChart(
        @Path("id") id: String,
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("days") days: String = "max"
    ): Observable<GeckoCoinChart>

}