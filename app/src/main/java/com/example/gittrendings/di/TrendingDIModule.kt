package com.example.gittrendings.di

import com.example.gittrendings.data.TrendingService
import com.example.gittrendings.domain.*
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class TrendingDIModule {

    @Provides
    fun providesTrendingService(retrofit: Retrofit): TrendingService =
        retrofit.create(TrendingService::class.java)
}

@Module
@InstallIn(ViewModelComponent::class)
interface TrendingRepositoryModule{

    @Binds
    fun bindTrendingRepository(defaultTrendingRepository: DefaultTrendingRepository): TrendingRepository

    @Binds
    fun bindTrendingDataUseCase(trendingDataUseCase: DefaultTrendingDataUseCase): TrendingDataUseCase

}