package com.example.test.bottom_nav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.test.models.FavVModel
import com.example.test.models.HistoryVModel
import com.example.test.models.ParamVModel
import com.example.test.models.VmFactory
import com.example.test.ui.theme.BarColor
import com.example.test.ui.theme.ScreenColor

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val paramVModel: ParamVModel = viewModel(key = "param", factory = VmFactory(navController, ParamVModel::class.java))
//    val favVModel: FavVModel = viewModel(factory = FavVModel.factory)
    val historyVModel: HistoryVModel = viewModel(factory = HistoryVModel.factory)

    Scaffold(
        containerColor = ScreenColor,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                colors = topAppBarColors(
                    containerColor = BarColor,
                ),
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text(text = "")
                    }
                },
            )
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        NavGraph(
            navHostController = navController,
            padding = it,
            paramVm = paramVModel,
            hisVm = historyVModel
        )
    }
}