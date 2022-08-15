package com.tunanh.clicktofood.data.remote

import com.tunanh.clicktofood.data.remote.model.Photo
import com.tunanh.clicktofood.data.remote.service.AnimeService
import com.tunanh.clicktofood.data.remote.service.MangaService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val animeService: AnimeService,
    private val mangaService: MangaService
) {

    suspend fun getAllPhotos(): List<Photo> {
        return animeService.getAllPhoto()
    }
}
