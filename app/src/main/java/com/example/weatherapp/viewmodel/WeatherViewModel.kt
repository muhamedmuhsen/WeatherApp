package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.model.Api
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val base_url = "https://api.weatherapi.com"
const val API_KEY = "ef8f29ada2a94ae0ac370244252701"

class WeatherViewModel : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> get() = _weatherData

    private val retrofit = Retrofit.Builder()
        .baseUrl(base_url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val callable = retrofit.create(Api::class.java)

    fun getData(latAndLon: String?) {
        callable.getData(API_KEY,"$latAndLon").enqueue(
            object : Callback<WeatherResponse> {
                override fun onResponse(p0: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        _weatherData.value = response.body()
                        Log.i("ApiResponse", "Response Data:$_weatherData")
                    } else
                        Log.i("ApiResponse", "Response is not Successful")
                }

                override fun onFailure(p0: Call<WeatherResponse>, p1: Throwable) {
                    Log.i("error", "${p1.message}")
                }
            })
    }

}