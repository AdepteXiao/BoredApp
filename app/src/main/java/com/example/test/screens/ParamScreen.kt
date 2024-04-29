package com.example.test.screens


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.test.bottom_nav.Route
import com.example.test.models.ParamVModel
import com.example.test.ui.theme.LightColor
import com.example.test.ui.theme.ScreenColor
import com.example.test.ui.theme.TextColor
import com.example.test.ui.theme.WindowsColor


@Composable
fun ParamScreen(
    vm: ParamVModel
) {
    Log.d("Param Screen", vm.toString())
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "WHAT TO DO?",
            color = TextColor,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 40.dp, end = 16.dp, bottom = 8.dp)
        )
        Card(
            colors = CardDefaults.cardColors(containerColor = WindowsColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                RowParam(
                    text = "Type",
                    choices =  arrayOf(
                        "any",
                        "education",
                        "recreational",
                        "social",
                        "diy",
                        "charity",
                        "cooking",
                        "relaxation",
                        "music",
                        "busywork"
                    ),
                    selected =  vm.type,
                    onSelectedChanged =  vm::setSelectedType
                )
                RowParam(
                    "People",
                    arrayOf("any", "1", "2", "3 - 4", "5 - 6"),
                    vm.people,
                    vm::setSelectedPeople
                )
                RowParam(
                    "Price",
                    arrayOf("any", "free", "cheap", "normal", "expensive"),
                    vm.price,
                    vm::setSelectedPrice
                )
            }
        }
        Button(
            onClick = {
                vm.generateIdeas(context)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = LightColor),
            shape = RoundedCornerShape(15.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 2.dp,
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


@Composable
fun RowParam(
    text: String, choices: Array<String>, selected: String,
    onSelectedChanged: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text,
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp)
                .weight(1f)
        )

        Demo_ExposedDropdownMenuBox(choices, selected, onSelectedChanged)

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox(
    choices: Array<String>, selected: String,
    onSelectedChanged: (String) -> Unit
) {
//    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selected) }


    ExposedDropdownMenuBox(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        TextField(
            colors = TextFieldDefaults.colors(
                focusedContainerColor = ScreenColor,
                unfocusedContainerColor = LightColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            shape = RoundedCornerShape(8.dp),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .defaultMinSize(160.dp)
                .size(160.dp, 50.dp)

        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            choices.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        selectedText = item
                        onSelectedChanged(item)
                        expanded = false
                    }
                )
            }
        }
    }

}



