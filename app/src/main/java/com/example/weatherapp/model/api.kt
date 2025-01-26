package com.example.weatherapp.model

import com.example.weatherapp.API_KEY
import retrofit2.Call
import retrofit2.http.GET

interface api {
    @GET("data/2.5/weather?lat=44.34&lon=10.99&appid=${API_KEY}")
    fun getPost():Call<WeatherData>
}