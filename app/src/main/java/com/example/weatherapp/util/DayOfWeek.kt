package com.example.weatherapp.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getDayOfWeek(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val date = LocalDate.parse(dateString, formatter)

    return date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
}
