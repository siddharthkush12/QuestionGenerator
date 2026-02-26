package com.example.questiongenerator.ui.Home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.questiongenerator.navigation.Login
import com.example.questiongenerator.ui.Home.dashboard.DashboardScreen
import com.example.questiongenerator.ui.Home.history.HistoryScreen
import com.example.questiongenerator.ui.Home.history.QuestionScreen
import com.example.questiongenerator.ui.Home.profile.ProfileScreen


@Composable
fun HomeNavGraph(
    navController: NavHostController,
    rootNavController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Dashboard
    ) {

        composable<Dashboard> {
            DashboardScreen()
        }

        composable<Profile> {
            ProfileScreen(
                onSignOut = {
                    rootNavController.navigate(Login) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable<History> {
            HistoryScreen(navController)
        }

        composable<Question> {backStackEntry->
            val args=backStackEntry.toRoute<Question>()
            QuestionScreen(args.mcqText)

        }
    }
}