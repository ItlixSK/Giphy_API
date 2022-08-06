package com.example.giphyapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.giphyapi.ulils.AppApplication

class GalleryViewModelFactory (private val application: AppApplication) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
            return GalleryViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
