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
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MainActivity : ComponentActivity() {

    private lateinit var fusedLocation: FusedLocationProviderClient
    var lat: Double? = null
    var long: Double? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        fusedLocation = LocationServices.getFusedLocationProviderClient(this)

        getLocation()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val pad = innerPadding

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
                lat = it.latitude
                long = it.longitude
            }
        }

    }

}

