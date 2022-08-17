package com.tunanh.clicktofood.data.remote.service

import com.tunanh.clicktofood.data.remote.model.Category
import com.tunanh.clicktofood.data.remote.model.Meals
import com.tunanh.clicktofood.data.remote.model.Slider
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface AnimeService {

    @GET
    suspend fun getAllPhoto(@Url url: String): List<Slider>

    @GET("categories.php")
    suspend fun getAllCategory(): List<Category>

    @GET("filter.php")
    suspend fun getAllFoodList(@Query("c") meals: String):List<Meals>
}