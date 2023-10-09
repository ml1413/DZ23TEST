package com.example.dz23test

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TestApiImpl {
    fun provideApi(): TestApi {
        return Retrofit.Builder()
            .baseUrl("https://api.coincap.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TestApi::class.java)
    }
}