package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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

        getLocation(this, this)

        val repository = WeatherRepositoryImpl()
        val factory = WeatherViewModelFactory(repository, application)
        weatherViewModel = ViewModelProvider(this, factory)[WeatherViewModel::class.java]

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val pa = innerPadding
                    WeatherScreen(weatherViewModel)
                }
            }
        }
    }
}

