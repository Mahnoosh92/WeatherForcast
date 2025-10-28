package com.mahnoosh.home.presentation

sealed interface HomeEvent {
    data object GetCities : HomeEvent
    data class Search(val city: String) : HomeEvent
    data class Increase(val id: Long): HomeEvent
}