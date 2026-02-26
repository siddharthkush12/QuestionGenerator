package com.example.questiongenerator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.questiongenerator.ui.Home.Home
import com.example.questiongenerator.ui.auth.LoginScreen
import com.example.questiongenerator.ui.auth.RegisterScreen
import com.example.questiongenerator.ui.splash.SplashScreen
import com.google.firebase.auth.FirebaseAuth


@Composable
fun NavGraph(
    navController: NavHostController,
){

    NavHost(
        navController = navController,
        startDestination = Splash

    ) {
        composable<Splash> {
            SplashScreen(navController)
        }

        composable<Login> {
            LoginScreen(
                onLoginSuccess = { navController.navigate(Home){
                    popUpTo(Login){
                        inclusive=true
                    }
                } },
                onRegisterClick = { navController.navigate(Signup) }
            )
        }

        composable<Signup> {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate(Home){
                    popUpTo(Signup){
                        inclusive=true
                    }
                } },
                onLoginClick = { navController.navigate(Login) }
            )
        }

        composable<Home>{
            Home(navController)
        }
    }
}