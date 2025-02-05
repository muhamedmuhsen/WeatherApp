package com.example.weatherapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.domain.model.Forecastday
import com.example.weatherapp.util.weatherState

@Composable
fun FutureItem(forecast: List<Forecastday>) {


    // LazyColumn {

    // itemsIndexed(forecast) { index, _ ->
    forecast.forEachIndexed {index,_->
        val day = forecast[index].date.split(" ")[0].slice(1..3)
        val icon = weatherState(forecast[index].day.condition.text)
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
            Spacer(Modifier.width(12.dp))
            Text(
                text = forecast[index].day.condition.text,
                modifier = Modifier
                    .weight(1f),
                fontSize = 14.sp,
                color = Color.White
            )
            Text(
                text = forecast[index].day.maxtemp_c.toString() + "°",
                modifier = Modifier.padding(end = 16.dp),
                fontSize = 14.sp,
                color = Color.White
            )
            Text(
                text = forecast[index].day.mintemp_c.toString() + "°",
                modifier = Modifier,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}