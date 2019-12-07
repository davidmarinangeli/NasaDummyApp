package com.example.nasabucket.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val APOD_ENDPOINT_KEY = "planetary/apod"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL+ APOD_ENDPOINT_KEY)
    .build()

interface MarsApiService {
    @GET("$APOD_ENDPOINT_KEY?api_key=$API_KEY")
    fun getProperties(): Deferred<NasaAPOD>
}

object MarsApi {
    val retrofitService: MarsApiService by lazy{
        retrofit.create(MarsApiService::class.java)
    }

}