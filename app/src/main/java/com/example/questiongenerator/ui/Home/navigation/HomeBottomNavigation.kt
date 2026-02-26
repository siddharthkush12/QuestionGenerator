package com.example.questiongenerator.ui.Home.navigation

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import com.example.questiongenerator.R

sealed class HomeBottomNavigation(
    val destination:Any,
    val title:String,
    val icon:Int
){
    object dashboard: HomeBottomNavigation(
        destination = Dashboard,
        title = "Dashboard",
        icon = R.drawable.home
    )
    object profile: HomeBottomNavigation(
        destination = Profile,
        title = "Profile",
        icon = R.drawable.profile
    )
    object history: HomeBottomNavigation(
        destination = History,
        title = "History",
        icon = R.drawable.chat
    )
}