package com.example.weatherapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R

@Composable
fun TodayStatusShape() {
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