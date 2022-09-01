package com.tunanh.clicktofood.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tunanh.clicktofood.data.local.dao.*
import com.tunanh.clicktofood.data.local.model.*

@Database(
    entities = [ User::class, Food::class, KeyWorkSearch::class],
    version = 12,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun foodDao(): FoodDao
    abstract fun historySearchDao():HistorySearchDao
}