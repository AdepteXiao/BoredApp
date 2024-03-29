package com.example.test.bottom_nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.test.screens.FavScreen
import com.example.test.screens.HistoryScreen
import com.example.test.screens.ParamScreen


@Composable
fun Screen1() {
    ParamScreen()
}
@Composable
fun Screen2() {
    FavScreen()
}
@Composable
fun Screen3() {
    HistoryScreen()
}
@Composable
fun Screen4() {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        text = "Screen 4",
        textAlign = TextAlign.Center
    )
}