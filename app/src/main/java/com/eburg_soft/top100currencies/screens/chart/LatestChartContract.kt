package com.eburg_soft.topcrypts.mvp.contract

import android.widget.FrameLayout
import com.eburg_soft.top100currencies.screens.base.BaseContract

class LatestChartContract {
    interface View : BaseContract.View {
        fun addEntryToChart(value: Float, date: String = "")
        fun addEntryToChart(date: Float, value: Float)
        fun showProgress()
        fun hideProgress()
        fun showErrorMessage(error: String?)
        fun refresh()
//        fun postInvalidateFrameLayout()
    }

    abstract class Presenter: BaseContract.Presenter<View>() {
        abstract fun makeChart(id: String)
        abstract fun refreshChart()
    }
}