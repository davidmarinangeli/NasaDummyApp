package com.example.nasabucket.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

open class PowerConnectionReceiver : BroadcastReceiver() {

    private val subject = PublishSubject.create<Int>()

    val observable :Observable<Int> = subject

    fun getObservable(inutile: String):Observable<Int>{
        return observable
    }

    override fun onReceive(context: Context, intent: Intent) {

        println("${intent.action} intent: $intent")

        when (intent.action) {

            "android.intent.action.ACTION_POWER_DISCONNECTED" -> subject.onNext(0)
            "android.intent.action.ACTION_POWER_CONNECTED" -> subject.onNext(1)
            else -> subject.onError(IllegalStateException("Indian error"))

        }


    }

}