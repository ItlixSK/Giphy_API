package com.example.giphyapi.data.api

import com.example.giphyapi.data.model.GifItem
import com.example.giphyapi.ulils.API_URL
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(API_URL)
    fun getSearch(
        @Query("q") q: String,
        @Query("api_key") apiKey: String?
    ): Call<GifItem>
}