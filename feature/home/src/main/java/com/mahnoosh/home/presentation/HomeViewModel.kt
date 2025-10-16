package com.mahnoosh.home.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahnoosh.home.domain.model.City
import com.mahnoosh.home.domain.usecase.city.CityUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val cityUsecase: CityUsecase) : ViewModel() {

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val homeUiState get() = _homeUiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery get() = _searchQuery.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetCities -> fetchCities()
            is HomeEvent.Search -> updateQuery(city = event.city)
        }
    }

    private fun updateQuery(city: String) {
        _searchQuery.value = city
    }

    @VisibleForTesting
    fun fetchCities() {
        _searchQuery
            .debounce(300)
            .distinctUntilChanged()
            .onStart {
                _homeUiState.update { HomeUiState.Loading }
            }
            .onEach {
                _homeUiState.update { HomeUiState.Loading }
                var resut: Result<List<City>>? = null
                resut = if (it.isEmpty())
                    cityUsecase("lon")
                else
                    cityUsecase(it)

                if (resut.isSuccess)
                    setHomeUiStateCities(resut.getOrNull() ?: emptyList())
                else
                    setHomeUiStateError("Something went wrong")
            }
            .catch { e ->
                setHomeUiStateError(e.message.toString())
            }
            .launchIn(viewModelScope)
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