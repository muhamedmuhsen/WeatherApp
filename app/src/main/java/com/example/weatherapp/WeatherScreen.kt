package com.example.weatherapp

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.location.FusedLocationProviderClient

const val API_KEY = "b5ec8672380323fa30103d985789a8a2"
const val base_url="https://api.openweathermap.org/"

@Preview(showSystemUi = true)
@Composable
private fun WeatherScreen() {

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
            Text(text = "Mostly Cloudy", color = Color.White, fontSize = 24.sp)
            Image(
                painter = painterResource(R.drawable.cloudy_sunny),
                contentDescription = null,
                Modifier
                    .size(220.dp)
                    .padding(vertical = 16.dp)
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Mon June 17 | ", color = Color.White, fontSize = 16.sp
                )
                Text(
                    text = " 10:00 AM", color = Color.White, fontSize = 16.sp
                )
            }
            Text(
                text = "25",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "H:27",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Text(
                    text = "L:17", color = Color.White, fontSize = 16.sp
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
}

@Preview(showSystemUi = false)
@Composable
private fun StatusShape() {
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
                    text = "22%",
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
                    text = "12 km/h",
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
                    text = "18%",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(text = "Humidity", color = Color.White, fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun TodayStatusShape() {
    Box(
        Modifier
            .padding(8.dp)
            .height(112.dp)
            .width(84.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(0xff311864))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = "7 am", color = Color.White)
            Image(
                painter = painterResource(R.drawable.rain),
                contentDescription = null,
                Modifier
                    .padding(vertical = 6.dp)
                    .size(36.dp)
            )
            Text(text = "7 am", color = Color.White)
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_8")
@Composable
private fun FutureItem() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // This will spread items across the row
    ) {
        Text(
            text = "Sat", fontSize = 14.sp, color = Color.White
        )
        Image(
            painter = painterResource(R.drawable.rain),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 32.dp)
                .size(45.dp)
        )
        Text(
            text = "Storm",
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            fontSize = 14.sp,
            color = Color.White
        )

        Text(
            text = "21",
            modifier = Modifier.padding(end = 16.dp),
            fontSize = 14.sp,
            color = Color.White
        )
        Text(
            text = "7", modifier = Modifier, fontSize = 14.sp, color = Color.White
        )
    }
}