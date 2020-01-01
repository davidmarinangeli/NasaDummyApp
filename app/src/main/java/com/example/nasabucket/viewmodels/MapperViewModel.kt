package com.example.nasabucket.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.nasabucket.utils.PowerConnectionReceiver

class MapperViewModel(
    val broadcast: PowerConnectionReceiver
) {

    val livedata = MutableLiveData<String>()

    @SuppressLint("CheckResult")
    fun init() {
        broadcast.observable
            .subscribe { livedata.value = ("ciao $it") }
    }

}