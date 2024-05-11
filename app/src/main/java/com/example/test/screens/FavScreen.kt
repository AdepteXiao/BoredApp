package com.example.test.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.R
import com.example.test.data.FavEntity
import com.example.test.data.HistoryEntity
import com.example.test.ui.theme.LightColor
import com.example.test.ui.theme.TextColor
import com.example.test.ui.theme.WindowsColor
import com.example.test.models.FavVModel

//@Preview(showBackground = true)
@Composable
fun FavScreen(vm: FavVModel = viewModel(factory = FavVModel.factory)) {

    var selectedItem by remember { mutableStateOf<FavEntity?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "FAVOURITE",
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
//            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(vm.favList) { favItem ->
                FavCard(
                    favItem,
                    onMove = { vm.moveItem(it) },
                    onCardClick = { selectedItem = it }
                )
            }
        }
        selectedItem?.let {
            FavDialog(
                onDismiss = { selectedItem = null },
                onButtonClick = { vm.deleteItem(it)
                    selectedItem = null },
                it.activity
            )
        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavCard(
    favItem: FavEntity,
    onMove: (FavEntity) -> Unit,
    onCardClick: (FavEntity) -> Unit
) {
    val tags = listOf(favItem.type, favItem.participants, favItem.price)
//    val maxLength = 55
    Card(
        colors = CardDefaults.cardColors(containerColor = WindowsColor),
        shape = RoundedCornerShape(26.dp),
        modifier = Modifier.padding(bottom = 16.dp),
        onClick = { onCardClick(favItem) }
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
                    text = favItem.activity,
                    maxLines = 2,
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
                    .clickable { onMove(favItem) },
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

@Composable
fun FavDialog(
    onDismiss: () -> Unit,
    onButtonClick: () -> Unit,
    activity: String
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .width(300.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = activity)
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Button(
                        onClick = {
                            onButtonClick()
                            onDismiss.invoke()
                        }
                    ) {
                        Text("Удалить")
                    }
                }
            }
        }
    }
}