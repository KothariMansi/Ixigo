package com.example.ixigo.ui.search

import com.example.ixigo.data.Platform
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class SearchState(
    val search: String = "",
    val platforms: Flow<List<Platform>> = flowOf(),
)