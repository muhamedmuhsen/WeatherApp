package com.example.weatherapp.util

import com.example.weatherapp.R

fun weatherState(data: String): Int {
    return when (data.lowercase()) {
        "overcast", "sunny", "clear" -> R.drawable.sunny
        "partly cloudy", "cloudy" -> R.drawable.cloudy
        "mist", "fog", "freezing fog" -> R.drawable.windy
        "patchy rain possible", "patchy light drizzle", "light drizzle", "patchy light rain", "light rain", "moderate rain at times", "moderate rain", "heavy rain at times", "heavy rain", "light rain shower", "moderate or heavy rain shower", "torrential rain shower" -> R.drawable.rain
        "patchy snow possible", "patchy sleet possible", "patchy freezing drizzle possible", "blowing snow", "blizzard", "patchy light snow", "light snow", "patchy moderate snow", "moderate snow", "patchy heavy snow", "heavy snow", "light snow showers", "moderate or heavy snow showers" -> R.drawable.snowy
        "thundery outbreaks possible", "patchy light rain with thunder", "moderate or heavy rain with thunder", "patchy light snow with thunder", "moderate or heavy snow with thunder" -> R.drawable.storm
        else -> R.drawable.sunny
    }
}