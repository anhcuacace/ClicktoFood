package com.tunanh.clicktofood.data.remote.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idCategory")
    val id: Long ?=null,
    @SerializedName("strCategory")
    val title: String ?=null,
    @SerializedName("strCategoryThumb")
    val image: String ?=null,
    @SerializedName("strCategoryDescription")
    val description: String ?=null
)