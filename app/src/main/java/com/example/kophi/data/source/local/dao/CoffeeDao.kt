package com.example.kophi.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kophi.data.source.local.entity.CartCoffeeEntity
import com.example.kophi.data.source.local.entity.CoffeeEntity

@Dao
interface CoffeeDao {
    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insertCoffee(coffee: CartCoffeeEntity)

    @Query("SELECT * FROM coffeeentity")
    suspend fun getAllItinerary(): List<CoffeeEntity>
}