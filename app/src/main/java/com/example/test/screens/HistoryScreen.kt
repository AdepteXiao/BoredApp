package com.example.test.screens

import android.widget.EditText
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.R
import com.example.test.ui.theme.BarColor
import com.example.test.ui.theme.GrayColor
import com.example.test.ui.theme.InvisColor
import com.example.test.ui.theme.LightColor
import com.example.test.ui.theme.ScreenColor
import com.example.test.ui.theme.TextColor
import com.example.test.ui.theme.WindowsColor

@Preview(showBackground = true)
@Composable
fun HistoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "ИСТОРИЯ",
            color = TextColor,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
//                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item{HistoryCard()}
            item{HistoryCard()}
            item{HistoryCard()}
            item{HistoryCard()}
            item{HistoryCard()}
            item{HistoryCard()}
            item{HistoryCard()}
            item{HistoryCard()}
            item{HistoryCard()}
            item{HistoryCard()}
            item{HistoryCard()}
            item{HistoryCard()}
            item{HistoryCard()}


        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryCard() {
    val name = "Learn Jetpack Compose and try not to cry"
    var place = "Город, Улица"
    var comment by remember {
        mutableStateOf("Как все прошло?")
    }
    Card(
        colors = CardDefaults.cardColors(containerColor = WindowsColor),
        shape = RoundedCornerShape(26.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
//                .padding(horizontal = 24.dp)
                .padding(bottom = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LightColor)
            ) {
                Text(
                    text = name, fontSize = 16.sp, modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp, vertical = 8.dp)
                )
            }

            TextField(
                value = comment,
                onValueChange = { newText -> comment = newText },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = WindowsColor,
                    unfocusedContainerColor = WindowsColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .align(Alignment.End)
                    .background(WindowsColor)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )
                Text(
                    text = place,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                IconButton(
                    onClick = { },
                    modifier = Modifier
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = "Геолокация"
                    )
                }
            }



        }
    }
}