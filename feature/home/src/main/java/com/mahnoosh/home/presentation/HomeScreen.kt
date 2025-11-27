package com.mahnoosh.home.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mahnoosh.home.presentation.component.LoadingItem
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()
    val query by viewModel.searchQuery.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeEvent.GetCities)
    }

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }) { padding ->
        Column(modifier = modifier
            .fillMaxSize()
            .padding(padding)) {
            TextField(modifier = Modifier.fillMaxWidth(), value = query, onValueChange = { viewModel.onEvent(HomeEvent.Search(it)) })

            Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
            ) {
                when (homeUiState) {
                    is HomeUiState.Cities -> {
                        LazyColumn {
                            if(homeUiState is HomeUiState.Cities){

                            }
                        }
                    }

                    is HomeUiState.Error -> {
                        LaunchedEffect((homeUiState as HomeUiState.Error).error) {
                            snackbarHostState.showSnackbar("Snackbar")
                        }
                    }

                    HomeUiState.Idle -> Unit
                    HomeUiState.Loading -> LoadingItem()
                }
            }
        }

    }
}