package com.example.weatherapp.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.lat
import com.example.weatherapp.lon
import com.example.weatherapp.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel.getData("$lat,$lon")
    }
    val data = viewModel.weatherData.collectAsState().value
    Log.i("ApiResponse", "Data:$data")

    if (data != null) {
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xff59469d), Color(0xff643d67)
                        )
                    )
                )
                .padding(16.dp)
        ) {

            Column(
                Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = data.current.condition.text, color = Color.White, fontSize = 24.sp)
                Image(
                    painter = painterResource(R.drawable.cloudy_sunny),
                    contentDescription = null,
                    Modifier
                        .size(220.dp)
                        .padding(vertical = 16.dp)
                )
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = data.location.localtime.split(" ")[0] + " | ",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                    Text(
                        text = " ${data.location.localtime}".split(" ")[2],
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                Text(
                    text = data.current.temp_c.toString() + "°C",
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = "H:${data.forecast.forecastday[0].day.maxtemp_c} °C",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Text(
                        text = "H:${data.forecast.forecastday[0].day.mintemp_c} °C",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                Spacer(Modifier.height(12.dp))
                StatusShape()
                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Today",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start)
                )

                LazyRow(
                    Modifier.fillMaxWidth()
                ) {
                    items(4) {
                        TodayStatusShape()
                    }
                }

                Text(
                    text = "Future",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start)
                )

                LazyColumn {
                    items(3) {
                        FutureItem()
                    }
                }
            }

        }
    } else {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    }
}





