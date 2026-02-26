package com.example.questiongenerator.data.dto


data class McqResponse(
    val success: Boolean,
    val title: String,
    val units: List<UnitMcq>
)

data class UnitMcq(
    val unit: String = "",
    val title: String = "",
    val mcqs: String = ""
)