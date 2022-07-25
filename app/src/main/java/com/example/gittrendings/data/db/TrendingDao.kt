package com.example.gittrendings.data.db

import androidx.room.*
import com.example.gittrendings.data.TrendingData

@Dao
interface TrendingDao {

    @Insert
    suspend fun insert(data: List<TrendingData>)

    @Query("SELECT * FROM trending")
    suspend fun getAllData() : List<TrendingData>

    @Query("DELETE FROM trending")
    suspend fun deleteData()
}