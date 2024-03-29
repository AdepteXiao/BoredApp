package com.example.test.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.R
import com.example.test.ui.theme.BarColor
import com.example.test.ui.theme.LightColor
import com.example.test.ui.theme.TextColor
import com.example.test.ui.theme.WindowsColor

@Preview(showBackground = true)
@Composable
fun FavScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "ИЗБРАННОЕ",
            color = TextColor,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
//                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { FavCard() }
            item { FavCard() }
            item { FavCard() }
            item { FavCard() }
            item { FavCard() }
            item { FavCard() }
            item { FavCard() }
            item { FavCard() }
            item { FavCard() }
            item { FavCard() }
            item { FavCard() }
            item { FavCard() }
            item { FavCard() }
            item { FavCard() }
        }


    }
}


@Composable
fun FavCard() {
    val tags = listOf("cheap", "3", "education")
    val name = "Learn Jetpack Compose and try not to cry "
    val longText = "This is a long text that exceeds the maximum allowed length"
    val maxLength = 60
    Card(
        colors = CardDefaults.cardColors(containerColor = WindowsColor),
        shape = RoundedCornerShape(26.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
//                    .fillMaxHeight()
                    .weight(1f)
                    .padding(start = 24.dp)
//                    .padding(top = 24.dp, bottom = 16.dp)
            ) {
                Text(
                    text = if (name.length > maxLength) {
                        "${name.take(maxLength)}..."
                    } else {
                        name
                    },
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 24.dp, bottom = 16.dp, end = 23.dp)
                )
                Row(
                    modifier = Modifier,
//                        .fillMaxWidth()
//                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    tags.forEach { tag ->
                        Card(
                            modifier = Modifier.padding(end = 16.dp),
                            colors = CardDefaults.cardColors(containerColor = LightColor)
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
                                text = tag,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically).width(60.dp)
//                    .weight(1f)
            ) {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LightColor)
//                        .width(60.dp)
//                        .fillMaxHeight()
//                        .weight(1f)
//                        .align(Alignment.CenterVertically)
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