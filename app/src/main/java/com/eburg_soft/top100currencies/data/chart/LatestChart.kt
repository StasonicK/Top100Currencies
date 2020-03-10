package com.eburg_soft.top100currencies.data.chart

import android.content.Context
import android.graphics.Color
import com.eburg_soft.top100currencies.R
import com.eburg_soft.top100currencies.common.App
import com.eburg_soft.top100currencies.common.extension.YearValueFormatter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import javax.inject.Inject

class LatestChart {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var formatter: YearValueFormatter

    lateinit var chart: LineChart

    init {
        App.appComponent.inject(this)
    }

    fun initChart(chart: LineChart) {
        this.chart = chart
        // enable description text
        chart.description.isEnabled = false
        // enable touch gestures
        chart.setTouchEnabled(true)
        // enable scaling and dragging
        chart.isDragEnabled = true
        chart.setScaleEnabled(false)
        chart.isScaleXEnabled = true
        chart.setDrawGridBackground(false)
        chart.isDoubleTapToZoomEnabled = false
        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false)
        //Sets the maximum distance in screen dp a touch can be away from an entry to cause it to get highlighted
        chart.maxHighlightDistance = 300F
        val data = LineData()
        data.setValueTextColor(Color.BLACK)
        // add empty data
        chart.data = data
        // get the legend (only possible after setting data)
        chart.legend.isEnabled = true
        //add marker
        chart.setDrawMarkers(true)
        chart.marker =
            MyMarkerView(context, R.layout.custom_marker_view)
        val xl = chart.xAxis
        xl.textColor = Color.BLACK
        xl.position = XAxis.XAxisPosition.BOTTOM
        xl.setDrawGridLines(false)
        xl.valueFormatter = formatter
        xl.labelCount = 3
        xl.granularity = 48F
        xl.setAvoidFirstLastClipping(true)
        xl.isEnabled = true
        val leftAxis = chart.axisLeft
        leftAxis.textColor = Color.BLACK
        leftAxis.setDrawGridLines(true)
        val rightAxis = chart.axisRight
        rightAxis.isEnabled = true

//        chart.invalidate()
//        chart.postInvalidate()
    }

    //add data to a chart
    fun addEntry(value: Float, date: Float) {
        val data = chart.data
        if (data != null) {
            var set: ILineDataSet? = data.getDataSetByIndex(0)
            if (set == null) {
                set = createSet()
                data.addDataSet(set)
            }
            data.addEntry(Entry(date, value), 0)
            data.notifyDataChanged()
            chart.notifyDataSetChanged()
            chart.moveViewToX(date)
            chart.highlightValue(date, 0)
        }

//        chart.invalidate()
//        chart.postInvalidate()

    }

    //create and adjust data set
    private fun createSet(): LineDataSet {
        val set = LineDataSet(null, "Price, USD")
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.cubicIntensity = 0.2f
        set.setDrawFilled(true)
        set.setDrawCircles(false)
        set.lineWidth = 1.8f
        set.circleRadius = 4f
        set.setCircleColor(Color.BLACK)
        set.highlightLineWidth = 1.2f
        set.highLightColor = context.resources.getColor(R.color.colorAccent)
        set.color = Color.BLACK
        set.fillColor = Color.BLACK
        set.enableDashedHighlightLine(10f, 5f, 0f)
        set.fillAlpha = 100
        set.setDrawHorizontalHighlightIndicator(true)
        set.setFillFormatter { _, _ ->
            chart.axisLeft.axisMinimum
        }
        return set
    }
}