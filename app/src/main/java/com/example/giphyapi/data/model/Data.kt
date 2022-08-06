package com.example.giphyapi.data.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: Images
)
