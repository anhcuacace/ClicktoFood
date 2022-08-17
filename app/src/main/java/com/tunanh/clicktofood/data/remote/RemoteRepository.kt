package com.tunanh.clicktofood.data.remote

import com.tunanh.clicktofood.data.remote.model.Category
import com.tunanh.clicktofood.data.remote.model.Slider
import com.tunanh.clicktofood.data.remote.service.AnimeService
import com.tunanh.clicktofood.data.remote.service.MangaService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val animeService: AnimeService,
    private val mangaService: MangaService
) {

    suspend fun getAllPhotos(): List<Slider> {
        return animeService.getAllPhoto("https://click-to-food-3639d-default-rtdb.firebaseio.com/app/slider.json")
    }
    suspend fun getAllCategory():List<Category> {
        return animeService.getAllCategory()
    }

}
