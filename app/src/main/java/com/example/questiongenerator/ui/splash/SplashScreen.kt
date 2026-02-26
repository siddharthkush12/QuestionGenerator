package com.example.questiongenerator.ui.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.questiongenerator.R
import com.example.questiongenerator.navigation.Home
import com.example.questiongenerator.navigation.Login
import com.example.questiongenerator.navigation.Splash
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SplashScreen(
    navController: NavHostController
){
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.study)
    )
    val progress by animateLottieCompositionAsState(
        composition=composition,
        iterations = 1,
        isPlaying = true
    )

    LaunchedEffect(progress) {
        if(progress>=0.99f){
            val user= FirebaseAuth.getInstance().currentUser
            if(user!=null){
                navController.navigate(Home){
                    popUpTo(Splash){
                        inclusive=true
                    }
                }
            }
            else{
                navController.navigate(Login){
                    popUpTo(Splash){
                        inclusive=true
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        LottieAnimation(
            composition=composition,
            progress={progress},
            modifier = Modifier.size(250.dp)
        )
    }
}