package com.example.gittrendings.data

import androidx.annotation.NonNull
import androidx.room.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrendingResponse(
    val items: List<TrendingData>
)

@JsonClass(generateAdapter = true)
@Entity(tableName = "trending")
data class TrendingData(
    @NonNull
    @PrimaryKey
    val id: String,
    val name: String?,
    val language: String?,
    @Json(name = "stargazers_count")
    val stargazersCount: Long?,
    val description: String?,
    @Embedded
    val owner: Owner
)

@JsonClass(generateAdapter = true)
data class Owner(
    val login: String,
    @Json(name = "avatar_url")
    val avatarUrl: String
)