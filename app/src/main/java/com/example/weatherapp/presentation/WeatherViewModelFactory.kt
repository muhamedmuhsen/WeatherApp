package com.example.weatherapp.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.domain.repository.WeatherRepository

class WeatherViewModelFactory(
    private val repository: WeatherRepository,
    private val application: Application
) : ViewModelProvider.Factory {
   override fun <T:ViewModel> create(modelClass:Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java))
            return WeatherViewModel(repository,application) as T
       throw IllegalArgumentException("Unknown ViewModel Class")
   }
}