package com.example.ixigo.data

data class UserJourney(
    val contactId: String,
    val itemsPerPage :Long,
    val startDate : String,
    val endDate: String,
    val pageNo: Int
)
