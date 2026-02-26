package com.example.questiongenerator.ui.Home.navigation

import kotlinx.serialization.Serializable

@Serializable
object Profile

@Serializable
object Dashboard

@Serializable
object History

@Serializable
data class Question(val mcqText: String)