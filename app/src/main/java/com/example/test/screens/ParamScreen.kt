package com.example.test.screens


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.*


@Preview(showBackground = true)
@Composable
fun ParamScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "ЧЕМ ЗАНЯТЬСЯ?",
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
                RowParam()
                RowParam()
                RowParam()
            }
        }
    }
}


@Composable
fun RowParam() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Кол-во человек",
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp).weight(1f)
        )

        Demo_ExposedDropdownMenuBox()

    }
}

//@SuppressLint("SuspiciousIndentation")
//@Composable
//fun DropdownMenuExample() {
//    var expanded by remember { mutableStateOf(false) }
//    var selectedOption by remember { mutableStateOf("Option 1") }
//    val options = listOf("Option 1", "Option 2", "Option 3")
//        TextButton(modifier = Modifier
//            .fillMaxWidth(),
////            .padding(16.dp),
//            onClick = { expanded = !expanded }) {
//            Text(text = "Select option")
//        }
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
////            modifier = Modifier.width(IntrinsicSize.Max)
//        ) {
//            options.forEach { option ->
//                DropdownMenuItem(
//                    text = { Text(text = option) },
//                    onClick = {
//                        selectedOption = option
//                        expanded = false
//                    })
//            }
//        }
//    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox() {
    val context = LocalContext.current
    val choice = arrayOf("education", "education", "education", "education", "education")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(choice[0]) }


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
            choice.forEach { item ->
                DropdownMenuItem(

                    text = { Text(text = item) },
                    onClick = {
                        selectedText = item
                        expanded = false
                        Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }

}



