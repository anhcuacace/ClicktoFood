package com.tunanh.clicktofood.data.remote.service

import com.tunanh.clicktofood.data.remote.model.Photo
import retrofit2.http.GET
import retrofit2.http.Path

interface MangaService {

    @GET("manga/{id}")
    suspend fun getMangaById(@Path("id") id: Int): Photo

    @GET("genre/manga/{id}")
    suspend fun getMangaByGenreId(@Path("id") id: Int): Photo

}