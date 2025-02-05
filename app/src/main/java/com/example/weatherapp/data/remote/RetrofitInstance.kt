package com.example.weatherapp.data.remote

import com.example.weatherapp.domain.remote.Api
import com.example.weatherapp.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val callable :Api= retrofit.create(Api::class.java)
}