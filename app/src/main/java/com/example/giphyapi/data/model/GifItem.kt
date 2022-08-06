package com.example.giphyapi.data.model

import com.google.gson.annotations.SerializedName

data class GifItem(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("pagination")
    val pagination: Pagination
)
