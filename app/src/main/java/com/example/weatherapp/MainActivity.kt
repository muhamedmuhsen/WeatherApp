package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.presentation.WeatherScreen
import com.example.weatherapp.presentation.WeatherViewModel
import com.example.weatherapp.presentation.WeatherViewModelFactory
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.util.getLocation


class MainActivity : ComponentActivity() {

    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        val repository = WeatherRepositoryImpl()
        val factory = WeatherViewModelFactory(repository, application)
        weatherViewModel = ViewModelProvider(this, factory)[WeatherViewModel::class.java]

        getLocation(this, this) { coordinates ->
            weatherViewModel.fetchData(coordinates)
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                WeatherScreen(weatherViewModel)
            }
        }
    }
}

