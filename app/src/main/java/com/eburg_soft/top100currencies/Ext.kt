package com.eburg_soft.top100currencies

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Formatter
import java.util.Locale

fun Float.formatThousands() : String {
    val sb = StringBuilder()
    val formatter = Formatter(sb, Locale.US)
    formatter.format("%(,.0f", this)
    return sb.toString()
}

fun Number.dateToString(pattern: String): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this.toLong()
    return SimpleDateFormat(pattern).format(calendar.time)
}