package com.mahnoosh.home.presentation.navigation

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mahnoosh.home.presentation.HomeScreen
import com.mahnoosh.home.presentation.HomeViewModel
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavGraphBuilder.homeScreen() {
    composable<HomeRoute>() {
        val viewModel = hiltViewModel<HomeViewModel>()
        HomeScreen(viewModel = viewModel)
    }
}