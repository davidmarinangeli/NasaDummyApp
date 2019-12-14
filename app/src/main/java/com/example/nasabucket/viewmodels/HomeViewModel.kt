package com.example.nasabucket.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasabucket.data.NasaMarsRover
import com.example.nasabucket.data.Photos
import com.example.nasabucket.network.NasaApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val roverImagesLiveData = MutableLiveData<List<Photos>>()

    val roverImagesList: LiveData<List<Photos>>
        get() = roverImagesLiveData

    init {
        getMarsRoverPicsList()
    }

    private fun getMarsRoverPicsList() {

        coroutineScope.launch {
            val deferred = NasaApi.retrofitService.getMarsRoverPhotosAsync(1000, "fhaz")
            val result = deferred.await()

            try {
                roverImagesLiveData.value = result.photos
            } catch (e: Exception) {

            }
        }

    }
}