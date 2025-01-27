package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.model.api
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val base_url = "https://api.openweathermap.org/"

class WeatherViewModel : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherData?>(null)
    val weatherData: StateFlow<WeatherData?> get() = _weatherData

    private val retrofit = Retrofit.Builder()
        .baseUrl(base_url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val callable = retrofit.create(api::class.java)


    fun getData(lat: Double, lon: Double) {
        callable.getData(lat = lat, lon = lon).enqueue(
            object : Callback<WeatherData> {
                override fun onResponse(p0: Call<WeatherData>, response: Response<WeatherData>) {
                    if (response.isSuccessful) {
                        _weatherData.value = response.body()
                        Log.d("ApiResponse", "Response Data:$_weatherData")
                    } else
                        Log.d("ApiResponse", "Response is not Successful")
                }

                override fun onFailure(p0: Call<WeatherData>, p1: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }


}