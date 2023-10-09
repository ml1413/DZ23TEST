package com.example.dz23test.model

data class ResponseModel(
    val data: Data?
)

data class Data(
    val id: String,
    val symbol: String,
    val rateUsd: String,
)