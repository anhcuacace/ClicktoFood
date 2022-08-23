package com.tunanh.clicktofood.data.local.dao

import androidx.room.*
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

    @Query("SELECT EXISTS(SELECT * FROM Food WHERE id = :id)")
    fun isRowIsExist(id : Long) : Boolean

    @Query("DELETE FROM Food")
    fun deleteAllFood()

    @Update
    fun update(food: Food)

}