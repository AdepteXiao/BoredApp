package com.example.test.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.test.R
import com.example.test.bottom_nav.Route
import com.example.test.data.HistoryEntity
import com.example.test.models.HistoryVModel
import com.example.test.ui.theme.LightColor
import com.example.test.ui.theme.TextColor
import com.example.test.ui.theme.WindowsColor

@Composable
fun HistoryScreen(
    navHostController: NavHostController,
    viewModel: HistoryVModel = viewModel(factory = HistoryVModel.factory)
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "HISTORY",
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val itemList = viewModel.getList()
            for (i in itemList){
                item { HistoryCard(viewModel, i) {navHostController.navigate(Route.MapScreen)} }
            }
//            items(10)
//            {
//                HistoryCard(viewModel) {
//                    navHostController.navigate(Route.MapScreen)
//                }
//            }


        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryCard(viewModel: HistoryVModel, item: HistoryEntity,
    onMapIconClick: () -> Unit
) {
    val name = viewModel.activity
    var place = viewModel.location
    var comment by remember {
        mutableStateOf("How did it go?")
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
                    text = place.toString(),
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                IconButton(
                    onClick = onMapIconClick,
                    modifier = Modifier
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = "Location"
                    )
                }
            }



        }
    }
}