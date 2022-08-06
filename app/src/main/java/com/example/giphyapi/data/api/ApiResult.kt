package com.example.giphyapi.data.api

import java.io.Serializable

class ApiResult:Serializable {
    var id: String? = null
    var fullSizeGif: String? = null
    var downsizedGif: String? = null
    var title: String? = null
}