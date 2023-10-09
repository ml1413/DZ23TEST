package com.example.dz23test.Response

import com.example.dz23test.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/v2/rates/{cryptoName}")
    suspend fun getCryptoByName(
        @Path("cryptoName") name: String
    ): ResponseModel
}