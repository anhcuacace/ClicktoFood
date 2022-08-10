package com.tunanh.clicktofood.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tunanh.clicktofood.data.local.dao.FavouriteQuoteDao
import com.tunanh.clicktofood.data.local.dao.ItemDao
import com.tunanh.clicktofood.data.local.dao.UserDao
import com.tunanh.clicktofood.data.local.model.FavouriteQuote
import com.tunanh.clicktofood.data.local.model.Item
import com.tunanh.clicktofood.data.local.model.User

@Database(
    entities = [FavouriteQuote::class, Item::class,User::class],
    version = 2,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun favouriteQuoteDao(): FavouriteQuoteDao
    abstract fun itemDao(): ItemDao
    abstract fun userDao(): UserDao
}