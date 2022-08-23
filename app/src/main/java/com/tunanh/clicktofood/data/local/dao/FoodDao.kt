package com.tunanh.clicktofood.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tunanh.clicktofood.data.local.model.Food

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFood(food: Food)

    @Query("select * from Food")
    suspend fun getAllFood(): List<Food>

    @Query("DELETE FROM Food WHERE id = :id")
    fun deleteById(id: Long)

    @Query("select * FROM Food WHERE id= :id")
    fun selectFromId(id: Long):Food

    @Query("DELETE FROM Food")
    fun deleteAllFood()

}