package com.example.test.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
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
    vm: HistoryVModel,
    navHostController: NavHostController
) {
    var selectedItem by remember { mutableStateOf<HistoryEntity?>(null) }

    LaunchedEffect(Unit) {
        vm.getList()
    }
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
//            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(vm.historyList) { hisItem ->
                HistoryCard(
                    vm,
                    hisItem,
                    onMapIconClick = {
                        vm.initialiseMap(hisItem)
                        navHostController.navigate(Route.MapScreen)
                    },
                    onNoteCompleted = { vm.updateItem(it) },
                    onCardClick = { selectedItem = it }
                )
            }
        }

        selectedItem?.let {
            HistoryDialog(
                onDismiss = { selectedItem = null },
                onButtonClick = {
                    vm.deleteItem(it)
                    selectedItem = null
                },
                it.activity
            )
        }
    }
}


@Composable
fun HistoryDialog(
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HistoryCard(
    viewModel: HistoryVModel, item: HistoryEntity,
    onMapIconClick: () -> Unit,
    onNoteCompleted: (HistoryEntity) -> Unit,
    onCardClick: (HistoryEntity) -> Unit
) {
    var place = viewModel.location
    var comment by rememberSaveable { mutableStateOf(item.note) }
    var isEditing by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Card(
        colors = CardDefaults.cardColors(containerColor = WindowsColor),
        shape = RoundedCornerShape(26.dp),
        modifier = Modifier.padding(bottom = 16.dp),
        onClick = { onCardClick(item) }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
                .padding(bottom = 2.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LightColor)
            ) {
                Text(
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    text = item.activity, fontSize = 16.sp, modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp, vertical = 8.dp)
                )
            }

            TextField(
                placeholder = {
                    Text(
                        text = "How did it go?",
                    )
                },
                value = if (isEditing) comment ?: "" else item.note ?: "",
                onValueChange = {
                    comment = it
                    isEditing = true
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = WindowsColor,
                    unfocusedContainerColor = WindowsColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    item.note = comment
                    onNoteCompleted(item)
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    isEditing = false
                }),
                modifier = Modifier
                    .heightIn(min = 80.dp, max = 200.dp)
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
                val parts = (item.location ?: "").split(",")
                val latitude = parts[0].trim().take(5)
                val longitude = parts.getOrNull(1)?.trim()?.take(5) ?: ""
                Text(
                    text = "$latitude  $longitude",
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