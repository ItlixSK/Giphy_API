package com.example.giphyapi.data.model

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("downsized")
    val downsized: Downsized,
    @SerializedName("original")
    val original: Original
)
