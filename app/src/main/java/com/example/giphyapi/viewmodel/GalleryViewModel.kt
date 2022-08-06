package com.example.giphyapi.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.giphyapi.data.api.ApiResult
import com.example.giphyapi.data.api.ApiService
import com.example.giphyapi.ulils.API_KEY
import com.example.giphyapi.ulils.AppApplication
import com.example.giphyapi.ulils.BASE_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class GalleryViewModel(application: AppApplication): AndroidViewModel(application) {

    var listGif : MutableList<ApiResult> = arrayListOf()

    private val gifsList = MutableLiveData<List<ApiResult>>()
    val gifList: LiveData<List<ApiResult>> get() = gifsList

    private val gifsSelected: MutableLiveData<ApiResult> = MutableLiveData()
    val gifSelected: LiveData<ApiResult> get() = gifsSelected

    private val _search: MutableLiveData<String> = MutableLiveData()
    val search: LiveData<String> get() = _search

    init {
        getGifs(q = "Cat")
    }

    @SuppressLint("LogNotTimber")
    private fun getGifs(q: String) {
        val requestGiphy = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        viewModelScope.launch(Dispatchers.IO) {
            listGif.clear()
            val response = try{
                requestGiphy.getSearch(apiKey = API_KEY, q = q).awaitResponse()
            } catch (e: IOException) {
                return@launch
            } catch (e: HttpException) {
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                val gifItem = response.body()!!
                for (i in 1..49) {
                    val itemGif = ApiResult()
                    itemGif.id = gifItem.data[i].id
                    itemGif.fullSizeGif = gifItem.data[i].images.original.url
                    itemGif.downsizedGif = gifItem.data[i].images.downsized.url
                    listGif.add(itemGif)
                }

                withContext(Dispatchers.Main){
                    gifsList.value = listGif
                }
            } else
                Log.e("data","data is not received")
        }

    }

    fun gifDetails(gif: ApiResult) {
        gifsSelected.value = gif
    }

    fun gifDetailsComplete() {
        gifsSelected.value = null
    }

    fun searchGif(a: String) {
        _search.value = a
        getGifs(a)
    }

    fun gifSearchComplete() {
        _search.value = null
    }
}