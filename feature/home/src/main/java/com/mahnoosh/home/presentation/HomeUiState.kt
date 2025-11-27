package com.mahnoosh.home.presentation

import androidx.compose.runtime.Immutable
import com.mahnoosh.model.data.City

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data object Idle : HomeUiState

    @Immutable
    data class Cities(val cities: List<City>) : HomeUiState

    data class Error(val error:String): HomeUiState
}