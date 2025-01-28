package com.example.weatherapp

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.screens.WeatherScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

var lat: String? = "34.052235"
var lon: String? = "-118.243683"

class MainActivity : ComponentActivity() {

    private lateinit var fusedLocation: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {

        fusedLocation = LocationServices.getFusedLocationProviderClient(this)

        getLocation()

        val viewModel=ViewModelProvider(this)[WeatherViewModel::class.java]
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val pad = innerPadding
                    WeatherScreen(viewModel)
                }
            }
        }
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100
            )
            return
        }

        val loction = fusedLocation.lastLocation
        loction.addOnSuccessListener {
            if (it != null) {
                lat = it.latitude.toString()
                lon = it.longitude.toString()
            }
        }

    }

}

