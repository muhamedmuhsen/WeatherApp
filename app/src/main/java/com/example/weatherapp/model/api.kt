package com.example.weatherapp.model

import com.example.weatherapp.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface api {
    @GET("data/2.5/weather?lat={latitude}&lon={longitude}&appid=${API_KEY}")
    fun getData(
        @Path("latitude") lat: Double,
        @Path("longitude") lon: Double,
    ): Call<WeatherData>
}