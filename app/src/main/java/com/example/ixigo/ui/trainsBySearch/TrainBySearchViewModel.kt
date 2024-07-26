package com.example.ixigo.ui.trainsBySearch

import androidx.lifecycle.ViewModel
import com.example.ixigo.data.Train
import com.example.ixigo.data.TrainSearch
import com.example.ixigo.repository.TrainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TrainBySearchViewModel @Inject constructor(
    private val trainRepository: TrainRepository
): ViewModel() {
    fun getTrains(
        trainSearch: TrainSearch
    ): Flow<List<Train>> {
        return trainRepository.getTrainsByTrainSearch(trainSearch)
    }

}
