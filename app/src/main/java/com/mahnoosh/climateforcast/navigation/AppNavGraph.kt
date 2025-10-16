package com.mahnoosh.climateforcast.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mahnoosh.home.presentation.navigation.HomeRoute
import com.mahnoosh.home.presentation.navigation.homeScreen


@Composable
fun AppNavGraph(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(modifier = modifier, navController = navController, startDestination = HomeRoute) {
        homeScreen()
    }
}