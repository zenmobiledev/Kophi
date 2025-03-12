package com.mobbelldev.kophi.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mobbelldev.kophi.data.source.local.dao.CoffeeCartDao
import com.mobbelldev.kophi.data.source.local.entity.CoffeeCartEntity

@Database(
    entities = [CoffeeCartEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun coffeeCartDao(): CoffeeCartDao
}