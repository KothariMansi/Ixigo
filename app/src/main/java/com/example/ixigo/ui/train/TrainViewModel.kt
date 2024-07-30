package com.example.ixigo.ui.train

import androidx.lifecycle.ViewModel
import com.example.ixigo.repository.TrainSearchRepository
import com.example.ixigo.ui.search.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
open class TrainViewModel @Inject constructor(
    platFormSearchRepository: TrainSearchRepository,
    ): ViewModel() {

    val _uiState = MutableStateFlow(TrainState())
    var uiState = _uiState.asStateFlow()
    private val _searchUiState = MutableStateFlow(SearchState())
    var searchUiState = _searchUiState.asStateFlow()
    private val platform = platFormSearchRepository.getPlatform()

    init {
        _searchUiState.update {
            it.copy(
                platforms = platform
            )
        }
    }

    fun updateIsArrivalIDepartureIsDate(
        isArrival: Boolean = false,
        isDeparture: Boolean = false,
        isDateSelected: Boolean = false
    ) {
        _uiState.update {
            it.copy(
                isArrival = isArrival,
                isDeparture = isDeparture,
                isDateSelected = isDateSelected
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
            ) }
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

    fun updatingCalender(selectedDateText: String) {
        _uiState.update { it.copy(
            selectedDateText = selectedDateText
        ) }
    }

    fun updateSearch(search: String) {
        _searchUiState.update { it.copy(
            search = search
        ) }
    }
}

data class TrainState(
    val arrival: String = "arrival",
    val departure: String = "departure",
    val isArrival: Boolean = false,
    val isDeparture: Boolean = false,
    val isDateSelected: Boolean = false,
    val selectedDateText: String = "Date",
)