package com.example.test.bottom_nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.test.models.ParamVModel
import com.example.test.screens.FavScreen
import com.example.test.screens.HistoryScreen
import com.example.test.screens.IdeasScreen
import com.example.test.screens.MapScreen
import com.example.test.models.FavVModel
import com.example.test.models.HistoryVModel
import com.example.test.screens.ParamScreen


@Composable
fun NavGraph(
    navHostController: NavHostController,
    padding: PaddingValues,
    paramVm: ParamVModel,
    hisVm: HistoryVModel
) {
    NavHost(navController = navHostController,
            startDestination = Route.HistoryScreen,
            modifier = Modifier.padding(padding)){
        composable(Route.GenerateScreen){
            ParamScreen(paramVm)
        }
        composable(Route.IdeaScreen){
            IdeasScreen(paramVm)
        }
        composable(Route.FavoriteScreen){
            FavScreen()
        }
        composable(Route.HistoryScreen){
            HistoryScreen(hisVm, navHostController)
        }
        composable(Route.MapScreen){
            MapScreen(hisVm, navHostController)
        }
    }
}