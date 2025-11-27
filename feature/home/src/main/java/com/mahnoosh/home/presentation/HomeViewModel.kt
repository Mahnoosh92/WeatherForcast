package com.mahnoosh.home.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.mahnoosh.model.data.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val homeUiState get() = _homeUiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery get() = _searchQuery.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetCities -> fetchCities()
            is HomeEvent.Search -> updateQuery(city = event.city)
            is HomeEvent.Increase -> increase(event.id)
        }
    }

    private fun increase(id: Long) {
        if (_homeUiState.value is HomeUiState.Cities) {
            val newCities = (_homeUiState.value as HomeUiState.Cities).cities.map {
                if(it.id==id)
                    it.copy(id = id+1)
                else
                    it
            }
            setHomeUiStateCities(cities = newCities)
        }
    }

    private fun updateQuery(city: String) {
        _searchQuery.value = city
    }

    @VisibleForTesting
    fun fetchCities() {

    }

    fun setHomeUiStateCities(cities: List<City>) {
        _homeUiState.update {
            HomeUiState.Cities(cities = cities)
        }
    }

    fun setHomeUiStateError(error: String) {
        _homeUiState.update {
            HomeUiState.Error(error = error)
        }
    }
}