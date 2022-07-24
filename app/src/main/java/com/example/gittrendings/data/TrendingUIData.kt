package com.example.gittrendings.data

import androidx.annotation.Keep

@Keep
data class TrendingUIData(
    val id: String,
    val name: String,
    val fullName: String,
    val language: String,
    val stargazersCount: String,
    val description: String,
    val image: String
)
