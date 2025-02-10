package com.example.weatherapp.presentation.components

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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.WeatherResponse

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
                TodayInfo(
                    text = data.forecast.forecastday[0].day.daily_chance_of_rain.toString() + "%",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                TodayInfo(text = "Rain", fontSize = 12.sp)
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
                TodayInfo(
                    data.current.wind_kph.toString() + " km/h",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                TodayInfo(text = "Wind speed", fontSize = 12.sp)
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
                TodayInfo(
                    text = data.current.humidity.toString() + " %",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                TodayInfo(text = "Humidity", fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun TodayInfo(
    text: String,
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = Color.White,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = color,
        modifier = modifier
    )
}