package com.example.weatherapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface api {
    @GET("/v1/current.json")
    fun getData(
        @Query("key")apiKey:String,
        @Query("q") latAndLon: String
    ): Call<WeatherResponse>
}