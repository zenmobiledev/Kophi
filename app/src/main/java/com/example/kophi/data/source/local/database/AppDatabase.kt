package com.example.kophi.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kophi.data.source.local.ListConverter
import com.example.kophi.data.source.local.dao.CoffeeDao
import com.example.kophi.data.source.local.entity.CoffeeCartEntity

@Database(
    entities = [CoffeeCartEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coffeeDao(): CoffeeDao
}