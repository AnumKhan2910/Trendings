package com.example.gittrendings.data

import com.squareup.moshi.Json

data class TrendingData(
    val id: String,
    val name: String,
    val language: String,
    @Json(name = "stargazers_count")
    val stargazersCount: Long,
    val owner: Owner
)

data class Owner(
    val login: String,
    @Json(name = "avatar_url")
    val avatarUrl: String
)