package com.example.kophi.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kophi.data.source.local.entity.CoffeeCartEntity

@Dao
interface CoffeeDao {
    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insertCoffeeCart(coffee: CoffeeCartEntity)

    @Query("SELECT * FROM coffeecartentity")
    suspend fun getAllCoffeeProducts(): List<CoffeeCartEntity>
}