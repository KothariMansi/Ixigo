package com.example.ixigo.repository

import com.example.ixigo.api.ApiService
import com.example.ixigo.data.Platform
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TrainSearchRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getPlatform(): Flow<List<Platform>> = flow {
            val platform = apiService.getPlatform()
            emit(platform)
        }.flowOn(Dispatchers.IO)

}