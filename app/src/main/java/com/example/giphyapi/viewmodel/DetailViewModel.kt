package com.example.giphyapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphyapi.data.api.ApiResult

class DetailViewModel(gif: ApiResult): ViewModel() {

    var item = gif

    private val _item = MutableLiveData<ApiResult>().apply { postValue(gif) }
    val liveItem: LiveData<ApiResult> get() = _item

    init {
        Log.e("detail","View Model created:")
    }

}