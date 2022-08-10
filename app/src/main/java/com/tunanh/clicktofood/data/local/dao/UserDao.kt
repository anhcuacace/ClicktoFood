package com.tunanh.clicktofood.data.local.dao

import androidx.room.*
import com.tunanh.clicktofood.data.local.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Query("DELETE FROM user WHERE email = :email")
    fun deleteByEmail(email: String)

    @Update
    fun update(user: User)

    @Query("select * from User where email = :email")
    suspend fun getItemWithEmail(email: String): User
}