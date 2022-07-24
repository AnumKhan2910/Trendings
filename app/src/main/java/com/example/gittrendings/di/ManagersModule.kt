package com.example.gittrendings.di

import com.example.gittrendings.utils.DefaultStringResourceManager
import com.example.gittrendings.utils.StringResourceManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ManagersModule {

    @Binds
    fun bindStringResourceManager(defaultStringResourceManager: DefaultStringResourceManager): StringResourceManager

}