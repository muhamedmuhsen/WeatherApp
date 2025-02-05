package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.remote.WeatherCallback

interface WeatherRepository {
    fun getData(coordinates: String,callback:WeatherCallback?)
}