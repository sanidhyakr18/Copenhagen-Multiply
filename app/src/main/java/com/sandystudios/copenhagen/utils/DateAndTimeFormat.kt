package com.sandystudios.copenhagen.utils

import java.text.SimpleDateFormat
import java.util.*

fun getDateAndTime(): String {
    val myFormat = "dd/MM/yyyy HH:mm:ss"
    val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
    return sdf.format(Date())
}