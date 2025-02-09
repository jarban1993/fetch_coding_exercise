package com.example.fetchcodingexercise

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

val BASE_URL="https://fetch-hiring.s3.amazonaws.com/"
interface GetHiringData {
    //get info from json
    @GET("hiring.json")
    fun getHiringInfo(): Call<HiringData>
}

object HiringDataInstance {
    val getHiringData:GetHiringData
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        getHiringData = retrofit.create(GetHiringData::class.java)
    }
}