package com.example.test.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.R
import com.example.test.data.FavEntity
import com.example.test.ui.theme.LightColor
import com.example.test.ui.theme.TextColor
import com.example.test.ui.theme.WindowsColor
import com.example.test.models.FavVModel
import com.example.test.models.ParamVModel

//@Preview(showBackground = true)
@Composable
fun FavScreen(viewModel: FavVModel = viewModel(factory = FavVModel.factory)) {
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

            val itemList = viewModel.getList()
            for (i in itemList){
                item { FavCard(i.type, i.participants, i.price, i.activity, viewModel, i) }
            }
        }


    }
}

@Composable
fun FavCard(type: String, people: String, price: String, name: String, viewModel: FavVModel, item: FavEntity) {
    val tags = listOf(type, people, price)
//    val longText = "This is a long text that exceeds the maximum allowed length"
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
                    .align(Alignment.CenterVertically)
                    .width(60.dp)
                    .background(LightColor)
                    .fillMaxHeight()
                    .clickable { viewModel.deleteItem(item) },
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),

                        painter = painterResource(id = R.drawable.check),
                        contentDescription = "Choose"
                    )
                }
            }
        }
    }
}