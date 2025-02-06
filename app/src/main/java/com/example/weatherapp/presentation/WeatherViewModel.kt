package com.example.weatherapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.weatherapp.domain.model.WeatherResponse
import com.example.weatherapp.domain.remote.WeatherCallback
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class WeatherViewModel(private val repo: WeatherRepository, application: Application) :
    AndroidViewModel(application) {

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> get() = _weatherData

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun fetchData(coordinates: String) {

        repo.getData(coordinates, object : WeatherCallback {

            override fun onSuccess(weatherResponse: WeatherResponse?) {

                Log.i("ApiResponseFromViewModel:", weatherResponse.toString())
                if (weatherResponse != null)
                    _weatherData.value = weatherResponse

            }

            override fun onFailure(error: String) {

                Log.i("ApiResponseFromViewModel:", error)
                _error.value = error

            }
        })
    }
}

