package com.example.dz23test

import com.example.dz23test.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TestApi {
    @GET("/v2/rates/{cryptoName}")
    suspend fun getCryptoByName(
        @Path("cryptoName") name: String
    ): Response<ResponseModel>
}