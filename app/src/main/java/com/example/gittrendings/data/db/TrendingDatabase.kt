package com.example.gittrendings.data.db

import android.content.Context
import androidx.room.*
import com.example.gittrendings.data.TrendingData

const val DB_VERSION = 1
const val DB_NAME ="database"

@Database(
    entities = [TrendingData::class],
    version = DB_VERSION,
    exportSchema = false
)

abstract class TrendingDatabase : RoomDatabase() {
    abstract fun trendingDao(): TrendingDao

    companion object {
        @Volatile
        private var INSTANCE: TrendingDatabase? = null

        fun getDatabase(context: Context): TrendingDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TrendingDatabase::class.java, DB_NAME
            ).build()
    }
}