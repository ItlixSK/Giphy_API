package com.example.giphyapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.giphyapi.data.api.ApiResult

class DetailViewModelFactory(private val gif:ApiResult) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(gif) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}