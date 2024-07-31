package com.example.ixigo.data

import kotlinx.serialization.Serializable

data class Train(
    val trainBase: TrainBase,
    val trainNum: Long,
    val aboutTrain: AboutTrain,
    val availability: String,
    val timeTaken: Long
)
data class TrainBase(
    val id: Int,
    val name: String,
    val trainSearch: TrainSearch
)

@Serializable
data class TrainSearch(
    val arrival: String,
    val departure: String,
    val date: String,
)

data class AboutTrain(
    val distance: String,
    val avgSpeed: String,
    val maxSpeedString: String,
)

data class Platform(
    val stationCode: String,
    val station: String
)