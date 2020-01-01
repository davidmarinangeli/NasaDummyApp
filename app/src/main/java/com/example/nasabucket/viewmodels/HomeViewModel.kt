package com.example.nasabucket.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasabucket.data.NasaMarsRover
import com.example.nasabucket.data.Photos
import com.example.nasabucket.network.NasaApi
import kotlinx.coroutines.*

class HomeViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val roverImagesList = MutableLiveData<List<Photos>>()

    init {
        getMarsRoverPicsList()
    }

    private fun getMarsRoverPicsList() {

        coroutineScope.launch {

            val result: NasaMarsRover = withContext(Dispatchers.IO) {
                NasaApi.retrofitService.getMarsRoverPhotosAsync(1000, "fhaz")
            }

            try {
                roverImagesList.value = result.photos
            } catch (e: Exception) {
                Log.e("HomeViewModel", "errore", e)
            }
        }

    }

    fun onAddRoverPhotoClicked() {
        roverImagesList.value?.also {
            addRover(it[it.size / 2]) // different index
        }
    }

    private fun addRover(photos: Photos) {
        val oldList = roverImagesList.value ?: emptyList()
        val newList = oldList.toMutableList().also { it.add(photos) }

        roverImagesList.value = newList.toList()
    }

    override fun onCleared() {
        viewModelJob.cancel(CancellationException("ferma tutto"))

        super.onCleared()
    }
}