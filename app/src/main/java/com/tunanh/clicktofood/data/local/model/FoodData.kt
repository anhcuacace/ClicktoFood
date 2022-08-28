package com.tunanh.clicktofood.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class FoodData(
    @PrimaryKey
    val id: Long = 0,
    val title: String = "",
    val cost: Int = 0,
    var like:Boolean,
    val star: Double? = 0.0,
    val img: String? = ""
)