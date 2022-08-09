package com.tunanh.clicktofood.data.remote.service

import com.tunanh.clicktofood.data.remote.model.Photo
import retrofit2.http.GET

interface AnimeService {

    @GET("photos")
    suspend fun getAllPhoto(): List<Photo>
}