package com.example.ixigo.ui.train

import androidx.lifecycle.ViewModel
import com.example.ixigo.data.Train
import com.example.ixigo.repository.TrainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TrainViewModel @Inject constructor(
    private val trainRepository: TrainRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(TrainState())
    var uiState = _uiState.asStateFlow()
    val trains: Flow<List<Train>> = trainRepository.getAllTrains()


}

data class TrainState(
    val name: String = ""
    // val trains: Flow<List<Train>> = flowOf()
)