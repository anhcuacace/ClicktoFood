package com.tunanh.clicktofood.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tunanh.clicktofood.data.local.model.FoodData

@Dao
interface FoodDataDao {
    @Query("DELETE FROM FoodData")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserts(foodList: List<FoodData>?)

    @Query("SELECT * FROM FoodData ORDER BY id DESC")
    suspend fun getFoodList(): List<FoodData>?

    @Query("SELECT * FROM FoodData WHERE id = :id ")
    suspend fun getFoodById(id: Long): FoodData?

    @Query("SELECT * FROM FoodData WHERE title LIKE '%' || :name  || '%' ORDER BY id DESC")
    suspend fun findFoodByName(name: String): List<FoodData>
}