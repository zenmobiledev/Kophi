package com.mobbelldev.kophi.di

import com.mobbelldev.kophi.data.mapper.Mapper
import com.mobbelldev.kophi.data.repository.CoffeeRepositoryImpl
import com.mobbelldev.kophi.data.source.local.dao.CoffeeCartDao
import com.mobbelldev.kophi.data.source.local.datasource.CoffeeLocalDataSource
import com.mobbelldev.kophi.data.source.local.datasource.CoffeeLocalDataSourceImpl
import com.mobbelldev.kophi.data.source.local.preference.PreferenceDataStore
import com.mobbelldev.kophi.data.source.remote.api.CoffeeService
import com.mobbelldev.kophi.data.source.remote.datasource.CoffeeRemoteDataSource
import com.mobbelldev.kophi.data.source.remote.datasource.CoffeeRemoteDataSourceImpl
import com.mobbelldev.kophi.domain.repositories.CoffeeRepository
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