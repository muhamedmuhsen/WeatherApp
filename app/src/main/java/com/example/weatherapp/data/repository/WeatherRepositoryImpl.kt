package com.example.weatherapp.data.repository

import android.util.Log
import com.example.weatherapp.domain.model.WeatherResponse
import com.example.weatherapp.domain.remote.WeatherCallback
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.util.Constants
import com.example.weatherapp.data.remote.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepositoryImpl : WeatherRepository {

    private val instance = RetrofitInstance.callable

    override fun getData(coordinates: String, callback: WeatherCallback?) {

        if (callback == null) return

        instance.getData(Constants.apiKey, coordinates).enqueue(

            object : Callback<WeatherResponse> {

                override fun onResponse(
                    p0: Call<WeatherResponse>, response: Response<WeatherResponse>
                ) {

                    if (response.isSuccessful) {
                        Log.i("ApiResponseRepoS:",response.body().toString())
                        callback.onSuccess(response.body())
                    } else {
                        Log.i("ApiResponseRepoF:",response.body().toString())
                        callback.onFailure("Error")
                    }
                }

                override fun onFailure(p0: Call<WeatherResponse>, p1: Throwable) {
                    callback.onFailure(p1.message.toString())
                    Log.i("error", "${p1.message}")
                }
            })
    }

}