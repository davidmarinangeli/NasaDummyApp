package com.example.nasabucket.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val APOD_ENDPOINT_KEY = "planetary/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface NasaApiService {
    @GET("${APOD_ENDPOINT_KEY}apod?api_key=$API_KEY")
    fun getAPODAsync(): Deferred<NasaAPOD>
}

object NasaApi {
    val retrofitService: NasaApiService by lazy{
        retrofit.create(NasaApiService::class.java)
    }

}