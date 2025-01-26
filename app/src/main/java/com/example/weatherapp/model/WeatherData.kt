package com.example.weatherapp.model

data class WeatherData(
    val description:String,
    val lat:Double,
    val long:Double,
    val temp:Int,
    val temp_max:Int,
    val temp_min:Int,
    val speed:Int,
    val humidity:Int,
    val rain:Int
)
