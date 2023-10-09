package com.example.dz23test.module

import com.example.dz23test.Response.ApiClient
import com.example.dz23test.Response.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun getApiClient(): ApiClient {
        return ApiClient()
    }

    @Provides
    @Singleton
    fun getRepository(apiClient: ApiClient): Repository {
        return Repository(retrofit = apiClient)
    }
}