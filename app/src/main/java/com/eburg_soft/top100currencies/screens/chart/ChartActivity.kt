package com.eburg_soft.top100currencies.screens.chart

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.eburg_soft.top100currencies.R
import com.eburg_soft.top100currencies.common.App
import com.eburg_soft.top100currencies.data.chart.LatestChart
import com.eburg_soft.topcrypts.mvp.contract.LatestChartContract
import com.eburg_soft.topcrypts.mvp.presenter.LatestChartPresenter
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_chart.chart_currency
import kotlinx.android.synthetic.main.activity_chart.frame_chart_container
import kotlinx.android.synthetic.main.activity_chart.image_currency_detail_icon
import kotlinx.android.synthetic.main.activity_chart.progress_chart
import kotlinx.android.synthetic.main.activity_chart.text_ATH
import kotlinx.android.synthetic.main.activity_chart.text_ath_change
import kotlinx.android.synthetic.main.activity_chart.text_circulating_supply
import kotlinx.android.synthetic.main.activity_chart.text_detail_market_cap_rank
import kotlinx.android.synthetic.main.activity_chart.text_market_cap_change
import kotlinx.android.synthetic.main.activity_chart.text_total_supply
import java.text.DecimalFormat
import javax.inject.Inject

class ChartActivity : AppCompatActivity(), OnChartValueSelectedListener, LatestChartContract.View {

    @Inject
    lateinit var latestChart: LatestChart

    lateinit var frameLayout: FrameLayout

    @Inject
    lateinit var presenter: LatestChartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        App.appComponent.inject(this)
        presenter.attach(this)

        frameLayout = findViewById(R.id.frame_chart_container)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra("name")
        val marketCapRank = intent.getIntExtra("marketCapRank", 0)
        val symbol = intent.getStringExtra("symbol")
        val marketCap = intent.getStringExtra("marketCap")
        val marketCapChangePercentage24h =
            intent?.getFloatExtra("marketCapChangePercentage24h", 0.0f)
        val priceChangePercentage24h = intent?.getFloatExtra(
            "priceChangePercentage24h",
            0.0f
        )
        val totalVolume = intent?.getFloatExtra("totalVolume", 0.0f)
        val ath = intent?.getFloatExtra("ath", 0.0f)
        val athChangePercentage = intent?.getFloatExtra("athChangePercentage", 0.0f)
        val circulatingSupply = intent?.getDoubleExtra("circulatingSupply", 0.0)
        val totalSupply = intent?.getLongExtra("totalSupply", 0)
        val image = intent.getStringExtra("image")
        Glide.with(this).load(image).into(image_currency_detail_icon)
        supportActionBar?.title = name
        val df = DecimalFormat("#")
        df.maximumFractionDigits = 2

        text_detail_market_cap_rank.text = marketCapRank.toString()
        text_market_cap_change.text = marketCapChangePercentage24h.toString()
        text_ATH.text = ath.toString()
        text_ath_change.text = df.format(athChangePercentage)
        text_circulating_supply.text = df.format(circulatingSupply)
        text_total_supply.text = totalSupply.toString()

        presenter.makeChart(intent.getStringExtra("id"))

        latestChart.initChart(chart_currency)

        frame_chart_container.invalidate()
        frame_chart_container.postInvalidate()
    }

    override fun onNothingSelected() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addEntryToChart(value: Float, date: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addEntryToChart(date: Float, value: Float) {
        latestChart.addEntry(value, date)
    }

    override fun showProgress() {
        progress_chart.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_chart.visibility = View.INVISIBLE
    }

    override fun showErrorMessage(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun refresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }
}