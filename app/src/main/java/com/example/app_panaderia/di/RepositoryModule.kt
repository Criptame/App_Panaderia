package com.example.app_panaderia.di

import com.example.app_panaderia.data.repository.PanRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePanRepository(
        localDataSource: com.example.app_panaderia.data.local.dao.PanDao,
        remoteDataSource: com.example.app_panaderia.data.remote.PanRemoteDataSource
    ): PanRepository {
        return PanRepository(localDataSource, remoteDataSource)
    }
}