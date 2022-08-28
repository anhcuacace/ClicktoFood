package com.tunanh.clicktofood.data.remote

import com.tunanh.clicktofood.data.remote.model.Categories
import com.tunanh.clicktofood.data.remote.model.Meals
import com.tunanh.clicktofood.data.remote.model.MealsForId
import com.tunanh.clicktofood.data.remote.model.Slider
import com.tunanh.clicktofood.data.remote.service.AnimeService
import com.tunanh.clicktofood.data.remote.service.MangaService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val animeService: AnimeService
) {

    suspend fun getAllPhotos(): List<Slider> {
        return animeService.getAllPhoto("https://click-to-food-3639d-default-rtdb.firebaseio.com/app/slider.json")
    }

    suspend fun getAllCategory(): Categories {
        return animeService.getAllCategory()
    }

    suspend fun getAllPhoToList(c: String): Meals {
        return animeService.getAllFoodList(c)
    }

    suspend fun getFood(id: Long): MealsForId {
        return animeService.getFood(id)
    }

    suspend fun getIdFood(token:String):List<Long>{
        return animeService.getAllId("https://click-to-food-3639d-default-rtdb.firebaseio.com/app/user/$token/card.json")
    }
}
