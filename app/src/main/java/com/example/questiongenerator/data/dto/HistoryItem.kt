package com.example.questiongenerator.data.dto



data class HistoryItem(
    val id: String = "",
    val title: String = "",
    val timestamp: Long = 0L,
    val units: List<UnitMcq> = emptyList()
)

