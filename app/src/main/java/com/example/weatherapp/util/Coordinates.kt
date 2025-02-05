package com.example.weatherapp.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices


var coordinates: String=""

fun getLocation(context: Context,activity: Activity) {

    val fusedLocation = LocationServices.getFusedLocationProviderClient(activity)

    if (ActivityCompat.checkSelfPermission(
            context, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context, android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100
        )
        return
    }

    val location = fusedLocation.lastLocation

    location.addOnSuccessListener {
        if (it != null) {
            Log.i("Coordinates:", "${it.latitude},${it.longitude}")
            coordinates = "${it.latitude},${it.longitude}"
        }
        else
            coordinates=Constants.defaultCoordinate
    }

}