package com.example.nasabucket.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasabucket.network.NasaAPOD
import com.example.nasabucket.network.NasaApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class DashboardViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        retrieveAPODfromNetwork()
    }

    private fun retrieveAPODfromNetwork() {


        coroutineScope.launch {
            val deferred = NasaApi.retrofitService.getAPODAsync()

            val result = deferred.await()

            try {
                _apod.value = result
            } catch (e: Exception){

            }
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "Dashboard fragment"
    }

    private val _apod = MutableLiveData<NasaAPOD>()

    val apod: LiveData<NasaAPOD>
        get() = _apod

    val text: LiveData<String> = _text
}