package com.example.weatherapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.model.WeatherResponse

@Composable
fun FutureItem(data: WeatherResponse, index: Int) {
    val day = getDayOfWeek(data.forecast.forecastday[index].date.split(" ")[0]).slice(0..3)
    val icon = weatherState(data.forecast.forecastday[index].day.condition.text)
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = day, fontSize = 14.sp, color = Color.White
        )
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 32.dp)
                .size(45.dp)
        )
        Text(
            text = data.forecast.forecastday[index].day.condition.text,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            fontSize = 14.sp,
            color = Color.White
        )

        Text(
            text = data.forecast.forecastday[index].day.maxtemp_c.toString() + "°",
            modifier = Modifier.padding(end = 16.dp),
            fontSize = 14.sp,
            color = Color.White
        )
        Text(
            text = data.forecast.forecastday[index].day.mintemp_c.toString() + "°",
            modifier = Modifier,
            fontSize = 14.sp,
            color = Color.White
        )
    }
}