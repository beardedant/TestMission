package com.testmission.utils

import java.text.SimpleDateFormat
import java.util.*

class TimeStampToNormalTime {
    fun toNormalDateFromString(timestamp: Long): String {
        val timestampLong = timestamp * 1000
        val d = Date(timestampLong)
        val c = Calendar.getInstance()
        c.time = d
        val minutes = c[Calendar.MINUTE]
        val hours = c[Calendar.HOUR_OF_DAY]

        return "$hours:$minutes"
    }
}