package com.example.ixigo.repository

import com.example.ixigo.api.ApiService
import com.example.ixigo.data.Train
import com.example.ixigo.data.TrainSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrainRepository @Inject constructor(
    private val apiService: ApiService
) {
     fun getAllTrains(): Flow<List<Train>> = flow {
         val trains =  apiService.getAllTrains()
         emit(trains)
     }.flowOn(Dispatchers.IO)

    fun getTrainsByTrainSearch(trainsSearch: TrainSearch) = flow {
        val trains = apiService.getTrainsByTrainSearch(
            trainsSearch.arrival,trainsSearch.departure,trainsSearch.date
        )
        emit(trains)
    }.flowOn(Dispatchers.IO)

}