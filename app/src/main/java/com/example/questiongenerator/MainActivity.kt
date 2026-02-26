package com.example.questiongenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.questiongenerator.data.api.ApiService
import com.example.questiongenerator.navigation.NavGraph

import com.example.questiongenerator.ui.theme.QuestionGeneratorTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuestionGeneratorTheme {
                val navController= rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

