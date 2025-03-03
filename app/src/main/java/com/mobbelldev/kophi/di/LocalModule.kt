package com.mobbelldev.kophi.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.mobbelldev.kophi.data.source.local.dao.CoffeeCartDao
import com.mobbelldev.kophi.data.source.local.database.AppDatabase
import com.mobbelldev.kophi.data.source.local.preference.PreferenceDataStore
import com.mobbelldev.kophi.data.source.local.preference.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun providePreferenceDataStore(dataStore: DataStore<Preferences>): PreferenceDataStore {
        return PreferenceDataStore(dataStore)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "coffee_db"
        ).build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): CoffeeCartDao {
        return database.coffeeCartDao()
    }
}