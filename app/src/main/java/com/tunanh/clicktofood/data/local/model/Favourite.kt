package com.tunanh.clicktofood.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favourite(
    @PrimaryKey val id: Long,
    val love:Boolean=true
)