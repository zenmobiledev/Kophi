package com.mobbelldev.kophi.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobbelldev.kophi.data.source.local.entity.CoffeeCartEntity

@Dao
interface CoffeeCartDao {
    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insertCoffeeCart(coffee: CoffeeCartEntity)

    @Query("SELECT * FROM coffeecartentity")
    suspend fun getAllCartCoffees(): List<CoffeeCartEntity>

    @Query("SELECT * FROM coffeecartentity WHERE coffeeId = :coffeeId AND temperature = :temperature AND milkOption = :milkOption AND sweetness = :sweetness LIMIT 1")
    suspend fun getCart(
        coffeeId: Int,
        temperature: String,
        milkOption: String,
        sweetness: String,
    ): CoffeeCartEntity

    @Query("UPDATE coffeecartentity SET quantity = :newQuantity, subtotal = price * :newQuantity WHERE id = :cartId")
    suspend fun updateCoffeeCartQuantityAndSubtotal(cartId: String, newQuantity: Int)

    // Increment quantity (+1) dan update subtotal
    @Query("UPDATE coffeecartentity SET quantity = quantity + 1, subtotal = price * (quantity + 1) WHERE id = :cartId")
    suspend fun incrementCoffeeCartQuantity(cartId: String)

    // Decrement quantity (-1) dan update subtotal
    @Query("UPDATE coffeecartentity SET quantity = quantity - 1, subtotal = price * (quantity - 1) WHERE id = :cartId")
    suspend fun decrementCoffeeCartQuantity(cartId: String)

    @Query("DELETE FROM coffeecartentity WHERE id = :cartId AND quantity == 1")
    suspend fun deleteCoffeeCart(cartId: String)
}