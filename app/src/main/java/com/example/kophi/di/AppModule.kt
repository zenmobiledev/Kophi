package com.example.kophi.di

import com.example.kophi.data.mapper.Mapper
import com.example.kophi.data.repository.CoffeeRepositoryImpl
import com.example.kophi.data.source.local.dao.CoffeeCartDao
import com.example.kophi.data.source.local.datasource.CoffeeLocalDataSource
import com.example.kophi.data.source.local.datasource.CoffeeLocalDataSourceImpl
import com.example.kophi.data.source.local.preference.PreferenceDataStore
import com.example.kophi.data.source.remote.api.CoffeeService
import com.example.kophi.data.source.remote.datasource.CoffeeRemoteDataSource
import com.example.kophi.data.source.remote.datasource.CoffeeRemoteDataSourceImpl
import com.example.kophi.domain.repositories.CoffeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUserRepository(
        localDataSource: CoffeeLocalDataSource,
        remoteDataSource: CoffeeRemoteDataSource,
        mapper: Mapper,
    ): CoffeeRepository {
        return CoffeeRepositoryImpl(
            coffeeLocalDataSource = localDataSource,
            coffeeRemoteDataSource = remoteDataSource,
            mapper = mapper
        )
    }

    @Provides
    @Singleton
    fun provideTravelRemoteDataSource(travelService: CoffeeService): CoffeeRemoteDataSource {
        return CoffeeRemoteDataSourceImpl(travelService)
    }

    @Provides
    @Singleton
    fun provideTravelLocalDataSource(
        preference: PreferenceDataStore,
        coffeeCartDao: CoffeeCartDao,
    ): CoffeeLocalDataSource {
        return CoffeeLocalDataSourceImpl(
            preference = preference,
            coffeeCartDao = coffeeCartDao
        )
    }
}