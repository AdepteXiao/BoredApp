package com.example.test.screens


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.R
import com.example.test.models.ParamVModel
import com.example.test.ui.theme.LightColor
import com.example.test.ui.theme.TextColor
import com.example.test.ui.theme.WindowsColor

@Composable
fun IdeasScreen(vm: ParamVModel) {
    Log.d("Idea Screen", vm.toString())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "IDEAS",
            color = TextColor,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 20.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IdeaCard(vm)

        }


    }
}


@Composable
fun IdeaCard(viewModel: ParamVModel) {
    val context = LocalContext.current
    val tags = listOf(viewModel.typeIdea, viewModel.peopleIdea.toString(), viewModel.priceIdea)
    val name = viewModel.activityIdea
    Card(
        colors = CardDefaults.cardColors(containerColor = WindowsColor),
        shape = RoundedCornerShape(45.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (viewModel.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier,
                color = TextColor
            )
        } else {
            Row(
                modifier = Modifier
//                        .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 40.dp),
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


//        Spacer(modifier = Modifier.height(30.dp))
            Card(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(55.dp),
                colors = CardDefaults.cardColors(containerColor = LightColor),
            ) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(vertical = 40.dp, horizontal = 32.dp)

                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { viewModel.saveGenerateIdeas(context) },
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart_boarder),
                        contentDescription = "Геолокация"
                    )
                }

                IconButton(
                    onClick = { viewModel.generateIdeas(context, false) },
                    modifier = Modifier
                        .fillMaxHeight()
//                        .width(60.dp)
//                        .fillMaxHeight()
//                        .weight(1f)
//                        .align(Alignment.CenterVertically)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_close_24),
                        contentDescription = "Ok"
                    )
                }
            }
        }
    }

}
//            Row(
//                modifier = Modifier
//                    .align(Alignment.CenterVertically)
////                    .weight(1f)
//            ) {
//                IconButton(
//                    onClick = { },
//                    modifier = Modifier
//                        .fillMaxHeight()
//                        .background(LightColor)
////                        .width(60.dp)
////                        .fillMaxHeight()
////                        .weight(1f)
////                        .align(Alignment.CenterVertically)
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.icon),
//                        contentDescription = "Геолокация"
//                    )
//                }
//            }


