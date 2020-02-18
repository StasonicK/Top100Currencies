package com.eburg_soft.top100currencies.chart

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.eburg_soft.top100currencies.R
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import info.eburg_soft.top100currencies.dateToString

@SuppressLint("ViewConstructor")
class MyMarkerView(context: Context, layoutResource: Int) : MarkerView(
    context,
    layoutResource
) {

    private val textContent: TextView

    init {
        textContent = findViewById(R.id.text_content)
    }

    // runs every time the MarkerView is redrawn, can be used to update the
// content (user-interface)
    override fun refreshContent(e: Entry, highlight: Highlight) {
        textContent.text = e.y.toString() + "\n" + e.x.dateToString("MMM dd, yyyy")
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }
}