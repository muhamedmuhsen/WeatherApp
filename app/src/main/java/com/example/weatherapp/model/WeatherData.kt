package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class WeatherData(
    val coord:Coord,
    val weather:List<Weather>,
    val main:Main,
    val wind: Wind,
    val rain:Rain
)

data class Coord(
    val lon:Double,
    val lat:Double,
)

data class Weather(
    val main:String
)

data class Main(
    val temp:Double,
    val temp_min:Double,
    val temp_max:Double,
    val humidity:Int,
)

data class Wind(
    val speed:Double
)

data class Rain(
    @SerializedName("1h")
    val rain:Double
)