package com.example.questiongenerator.ui.Home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.questiongenerator.ui.Home.navigation.BottomBar
import com.example.questiongenerator.ui.Home.navigation.HomeNavGraph


@Composable
fun Home(
    navController: NavHostController
){
    val homeNavController= rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(homeNavController)
        }
    ) {paddingValues->
        Box(modifier = Modifier.padding(paddingValues)) {
            HomeNavGraph(
                navController = homeNavController,
                rootNavController=navController
            )
        }
    }
}