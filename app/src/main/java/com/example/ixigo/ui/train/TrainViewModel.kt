package com.example.ixigo.ui.train

import androidx.lifecycle.ViewModel
import com.example.ixigo.data.Train
import com.example.ixigo.repository.TrainRepository
import com.example.ixigo.repository.TrainSearchRepository
import com.example.ixigo.ui.search.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
open class TrainViewModel @Inject constructor(
    trainRepository: TrainRepository,
    trainSearchRepository: TrainSearchRepository,
    ): ViewModel() {

    val _uiState = MutableStateFlow(TrainState())
    var uiState = _uiState.asStateFlow()
    val trains: Flow<List<Train>> = trainRepository.getAllTrains()
    private val _searchUiState = MutableStateFlow(SearchState())
    var searchUiState = _searchUiState.asStateFlow()
    private val platform = trainSearchRepository.getPlatform()

    init {
        _searchUiState.update {
            it.copy(
                platforms = platform
            )
        }
    }


    fun updateIsArrivalIDeparture(isArrival: Boolean,
                                  isDeparture: Boolean
    ) {
        _uiState.update {
            it.copy(
                isArrival = isArrival,
                isDeparture = isDeparture
            )
        }
    }

    fun updateArrivalOrDep(
        arrival: String = _uiState.value.arrival,
        departure: String = _uiState.value.departure
    ) {
        if (_uiState.value.isArrival){
            _uiState.update {
                it.copy(
                    arrival = arrival,
                    isArrival = false,
                    isDeparture = false
                )
            }
        } else if (_uiState.value.isDeparture){
            _uiState.update { it.copy(
                departure = departure,
                isArrival = false,
                isDeparture = false
            )
            }
        }
    }

    fun swap() {
        _uiState.update {
            it.copy(
                arrival = uiState.value.departure,
                departure = uiState.value.arrival
            )
        }
    }
}

data class TrainState(
    val arrival: String = "arrival",
    val departure: String = "departure",
    val date: String = "",
    val isArrival: Boolean = false,
    val isDeparture: Boolean = false
    // val trains: Flow<List<Train>> = flowOf()
)