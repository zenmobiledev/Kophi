package com.example.kophi.di

import android.content.Context
import androidx.room.Room
import com.example.kophi.data.source.local.dao.CoffeeCartDao
import com.example.kophi.data.source.local.database.AppDatabase
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