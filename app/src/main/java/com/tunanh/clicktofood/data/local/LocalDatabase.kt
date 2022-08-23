package com.tunanh.clicktofood.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tunanh.clicktofood.data.local.dao.*
import com.tunanh.clicktofood.data.local.model.*

@Database(
    entities = [FavouriteQuote::class, Item::class, User::class, Food::class],
    version = 3,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun favouriteQuoteDao(): FavouriteQuoteDao
    abstract fun itemDao(): ItemDao
    abstract fun userDao(): UserDao
    abstract fun foodDao(): FoodDao

}