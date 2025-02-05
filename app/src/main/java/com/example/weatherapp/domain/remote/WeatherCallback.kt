package com.example.weatherapp.domain.remote

import com.example.weatherapp.domain.model.WeatherResponse

interface WeatherCallback {
    fun onSuccess(weatherResponse: WeatherResponse?)
    fun onFailure(error:String)
}