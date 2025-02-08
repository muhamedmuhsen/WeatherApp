package com.example.weatherapp.util

import java.time.LocalDate

fun getDayOfWeek(dateString: String): String {

    val dateParts = dateString.split("-").map { it.toInt() }
    val date = LocalDate.of(dateParts[0], dateParts[1], dateParts[2])

    val day = date.dayOfWeek.toString().slice(0..2).lowercase()
        .replaceFirstChar { it.uppercase() } + " " + date.month.toString().lowercase()
        .replaceFirstChar { it.uppercase() } +" "+date.dayOfMonth

    return day
}
