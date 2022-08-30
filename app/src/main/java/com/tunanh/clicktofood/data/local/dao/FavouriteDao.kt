package com.tunanh.clicktofood.data.local.dao

import androidx.room.*
import com.tunanh.clicktofood.data.local.model.Favourite

@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuote(quote: Favourite)

    @Query("select * from Favourite")
    suspend fun getAllQuotes(): List<Favourite>

    @Delete
    suspend fun removeFromFavourite(quote: Favourite)

    @Query("SELECT EXISTS(SELECT * FROM Favourite WHERE id = :id)")
    suspend fun isRowIsExist(id: Double ) : Boolean

}