package com.example.dz23test.Response

import com.example.dz23test.model.ResponseModel

class Repository(private val retrofit: ApiClient) {
    suspend fun getCryptoByName(name: String): ResponseModel {
        val apiInterface = retrofit.client.create(ApiInterface::class.java)
        return apiInterface.getCryptoByName(name = name)
    }
}