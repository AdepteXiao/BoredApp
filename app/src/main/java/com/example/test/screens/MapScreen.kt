package com.example.test.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.test.R
import com.example.test.bottom_nav.Route
import com.example.test.ui.theme.LightColor
import com.example.test.ui.theme.TextColor

@Composable
fun MapScreen(
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
//            .padding(horizontal = 24.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(painter = painterResource(R.drawable.pix),
            contentDescription = "",
            modifier = Modifier.weight(1f),
            contentScale = ContentScale.Crop)
        Row(

            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Button(
                onClick = { navHostController.navigate(Route.HistoryScreen) },
                modifier = Modifier.weight(0.5f).height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LightColor),
                shape = RoundedCornerShape(2.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 1.dp,
                )
            ) {
                Text(
                    "Back",
                    fontSize = 16.sp,
                    color = TextColor
                )
            }
            Button(
                onClick = { navHostController.navigate(Route.HistoryScreen) },
                modifier = Modifier.weight(0.5f).height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LightColor),
                shape = RoundedCornerShape(2.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 1.dp,

                    )
            ) {
                Text(
                    "Submit",
                    fontSize = 16.sp,
                    color = TextColor
                )
            }
        }
    }
}
