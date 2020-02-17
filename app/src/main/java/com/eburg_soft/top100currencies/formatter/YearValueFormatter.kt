package com.eburg_soft.top100currencies.formatter

import com.eburg_soft.top100currencies.dateToString
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.util.Calendar

class YearValueFormatter:IAxisValueFormatter{
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = value.toLong()
        return calendar.toFormatted()
    }

    fun Calendar.toFormatted(): String {
        val date = this.timeInMillis
        return date.dateToString("MMM yyyy")
    }
}