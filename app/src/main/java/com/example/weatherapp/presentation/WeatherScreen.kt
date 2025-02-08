package com.example.weatherapp.presentation

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.presentation.components.FutureItem
import com.example.weatherapp.presentation.components.StatusShape
import com.example.weatherapp.presentation.components.TodayStatusShape
import com.example.weatherapp.util.getDayOfWeek
import com.example.weatherapp.util.weatherState

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
//    LaunchedEffect(key1 = true) {
//        viewModel.fetchData(coordinates)
//    }
    val data = viewModel.weatherData.collectAsState().value
    val error = viewModel.error
    Log.i("ApiResponse", "Data:$data")

    if (data != null) {
        val icon = weatherState(data.current.condition.text)
        val day = getDayOfWeek(data.current.last_updated.split(" ")[0])
        Scaffold { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state= rememberScrollState())
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
                // Main Content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Current Weather
                    Text(
                        text = data.current.condition.text, color = Color.White, fontSize = 24.sp
                    )
                    Image(
                        painter = painterResource(icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp)
                            .padding(vertical = 16.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "$day | ", color = Color.White, fontSize = 16.sp
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
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
                    Spacer(modifier = Modifier.height(12.dp))

                    // Status Shape (Rain, Wind, Humidity)
                    StatusShape(data)
                    Spacer(modifier = Modifier.height(12.dp))

                    // Today's Weather (Hourly)
                    Text(
                        text = "Today",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                    ) {
                        items(4) { index ->
                            TodayStatusShape(data, index + 6)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    // Future Forecast (Daily)
                    Text(
                        text = "Future",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    // Use a Column for future forecast items
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val forecastSize = data.forecast.forecastday.size
                        Log.i("forecastSize", "size:$forecastSize")

                        FutureItem(data.forecast.forecastday)
                    }
                }
            }
        }
//    } else if (error != null) {
//        Box(
//            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = error.toString(), color = Color.Red, fontSize = 18.sp
//            )
//        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}





