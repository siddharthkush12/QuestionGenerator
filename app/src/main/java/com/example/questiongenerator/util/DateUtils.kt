package com.example.questiongenerator.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {

    fun format(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}