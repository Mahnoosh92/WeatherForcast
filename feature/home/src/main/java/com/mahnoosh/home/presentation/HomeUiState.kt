package com.mahnoosh.home.presentation

import androidx.compose.runtime.Immutable
import com.mahnoosh.home.domain.model.City

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data object Idle : HomeUiState

    @Immutable
    data class Cities(val cities: List<City>) : HomeUiState

    data class Error(val error:String): HomeUiState
}