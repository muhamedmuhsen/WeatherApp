package com.example.weatherapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {
    @GET("/v1/forecast.json")
    fun getData(
        @Query("key")apiKey:String,
        @Query("q") latAndLon: String,
        @Query("days") days:String="4"
    ): Call<WeatherResponse>
}