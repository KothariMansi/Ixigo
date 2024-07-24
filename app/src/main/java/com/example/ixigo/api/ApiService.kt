package com.example.ixigo.api

import com.example.ixigo.data.Train
import com.example.ixigo.data.TrainSearch
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/trains")
    suspend fun getAllTrains(): List<Train>

    @GET("/trains/search")
    suspend fun getTrainsByTrainSearch(
        @Query("arrival") arrival: String,
        @Query("departure") departure: String,
        @Query("date") date: String,
    ): List<Train>
}