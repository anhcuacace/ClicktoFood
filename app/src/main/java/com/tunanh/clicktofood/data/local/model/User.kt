package com.tunanh.clicktofood.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    var email:String="",
    val name:String="",
    val age:Int=0,
    val image: String=""
)
