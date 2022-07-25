package com.example.gittrendings.di

import android.content.Context
import com.example.gittrendings.data.db.TrendingDao
import com.example.gittrendings.data.db.TrendingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): TrendingDatabase {
        return TrendingDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideProductDao(trendingDatabase: TrendingDatabase): TrendingDao {
        return trendingDatabase.trendingDao()
    }
}