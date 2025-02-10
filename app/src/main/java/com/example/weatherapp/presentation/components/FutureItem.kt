package com.example.weatherapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.domain.model.Forecastday
import com.example.weatherapp.util.getDayOfWeek
import com.example.weatherapp.util.weatherState

@Composable
fun FutureItem(forecast: List<Forecastday>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        itemsIndexed(forecast) { index, _ ->
            //forecast.forEachIndexed {index,_->
            val day = getDayOfWeek(forecast[index].date).slice(0..2)
            val icon = weatherState(forecast[index].day.condition.text)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FutureInfoText(day)
                Image(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 32.dp)
                        .size(45.dp)
                )
                Spacer(Modifier.width(12.dp))
                FutureInfoText(forecast[index].day.condition.text)
                FutureInfoText(
                    forecast[index].day.maxtemp_c.toString() + "°",
                    modifier = Modifier.padding(end = 16.dp)
                )
                FutureInfoText(forecast[index].day.mintemp_c.toString() + "°")
            }
        }
    }
}

@Composable
fun FutureInfoText(
    text: String,
    fontSize: TextUnit = 14.sp,
    color: Color = Color.White,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        modifier = modifier
    )
}