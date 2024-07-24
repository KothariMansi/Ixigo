package com.example.ixigo

import com.example.ixigo.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApModule {
    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return RetrofitInstance.api
    }
}