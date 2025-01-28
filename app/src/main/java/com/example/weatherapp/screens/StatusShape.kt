package com.example.weatherapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherResponse

@Composable
fun StatusShape(data: WeatherResponse) {
    Box(
        Modifier
            .fillMaxWidth(0.95f)
            .clip(RoundedCornerShape(22.dp))
            .background(color = Color(0xff311864))

    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.rain),
                    contentDescription = null,
                    Modifier
                        .padding(horizontal = 12.dp)
                        .size(36.dp)
                )
                Text(
                    text = data.forecast.forecastday[0].day.daily_chance_of_rain.toString() + "%",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(text = "Rain", color = Color.White, fontSize = 12.sp)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.wind),
                    contentDescription = null,
                    Modifier.size(36.dp)
                )
                Text(
                    text = data.current.wind_kph.toString() + " km/h",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(text = "Wind speed", color = Color.White, fontSize = 12.sp)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.humidity),
                    contentDescription = null,
                    Modifier
                        .padding(horizontal = 12.dp)
                        .size(36.dp)
                )
                Text(
                    text = data.current.humidity.toString() + " %",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(text = "Humidity", color = Color.White, fontSize = 12.sp)
            }
        }
    }
}