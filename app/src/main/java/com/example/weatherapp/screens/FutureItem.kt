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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

@Preview(showSystemUi = true, device = "id:pixel_8")
@Composable
fun FutureItem() {
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