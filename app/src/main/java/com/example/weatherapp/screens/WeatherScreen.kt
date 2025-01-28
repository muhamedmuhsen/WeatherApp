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
import androidx.compose.material3.Scaffold
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {

    LaunchedEffect(key1 = true) {
        viewModel.getData("$lat,$lon")
    }
    val data = viewModel.weatherData.collectAsState().value
    Log.i("ApiResponse", "Data:$data")

    if (data != null) {
        val icon = weatherState(data.current.condition.text)
        val day = getDayOfWeek(data.current.last_updated.split(" ")[0])
        Scaffold { innerPadding ->
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
                    .padding(innerPadding)
                    .padding(horizontal = 8.dp)

            ) {

                Column(
                    Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = data.current.condition.text, color = Color.White, fontSize = 24.sp)
                    Image(
                        painter = painterResource(icon),
                        contentDescription = null,
                        Modifier
                            .size(150.dp)
                            .padding(vertical = 16.dp)
                    )
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Text(
                            text = "$day | ",
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
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Text(
                            text = "H:" + data.forecast.forecastday[0].day.maxtemp_c + "°C",
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Text(
                            text = "L:" + data.forecast.forecastday[0].day.mintemp_c + "°C",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                    StatusShape(data)
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
                        items(4) { index ->
                            TodayStatusShape(data, index + 6)
                        }
                    }

                    Text(
                        text = "Future",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    LazyColumn(
                        modifier = Modifier.height(200.dp) // Adjust height as needed
                    ) {
                        items(3) { index ->
                            FutureItem(data, index)
                        }
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


fun weatherState(data: String): Int {
    return when (data.lowercase()) {
        "overcast", "sunny", "clear" -> R.drawable.sunny
        "partly cloudy", "cloudy" -> R.drawable.cloudy
        "mist", "fog", "freezing fog" -> R.drawable.windy
        "patchy rain possible", "patchy light drizzle", "light drizzle", "patchy light rain", "light rain", "moderate rain at times", "moderate rain", "heavy rain at times", "heavy rain", "light rain shower", "moderate or heavy rain shower", "torrential rain shower" -> R.drawable.rain
        "patchy snow possible", "patchy sleet possible", "patchy freezing drizzle possible", "blowing snow", "blizzard", "patchy light snow", "light snow", "patchy moderate snow", "moderate snow", "patchy heavy snow", "heavy snow", "light snow showers", "moderate or heavy snow showers" -> R.drawable.snowy
        "thundery outbreaks possible", "patchy light rain with thunder", "moderate or heavy rain with thunder", "patchy light snow with thunder", "moderate or heavy snow with thunder" -> R.drawable.storm
        else -> R.drawable.sunny
    }
}


fun getDayOfWeek(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val date = LocalDate.parse(dateString, formatter)

    return date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
}