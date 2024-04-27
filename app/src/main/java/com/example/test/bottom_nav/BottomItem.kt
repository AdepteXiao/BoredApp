package com.example.test.bottom_nav

import com.example.test.R

sealed class BottomItem(val title: String, val iconId: Int, val route: String){
    data object Screen1: BottomItem("Generate", R.drawable.lightbulb, Route.GenerateScreen)
    data object Screen2: BottomItem("Favourites", R.drawable.heart_boarder, Route.FavoriteScreen)
    data object Screen3: BottomItem("History", R.drawable.history, Route.HistoryScreen)
//    data object Screen4: BottomItem("Ideas", R.drawable.icon, Route.IdeaScreen)
}