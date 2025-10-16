package com.mahnoosh.home.presentation

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.mahnoosh.home.domain.model.City
import com.mahnoosh.home.domain.usecase.city.CityUsecase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val cityUsecase = mockk<CityUsecase>()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(cityUsecase = cityUsecase)
    }

    @Test
    fun `homeviewmodel initial state is idle`() {
        assertThat(viewModel.homeUiState.value).isEqualTo(HomeUiState.Idle)
    }

    @Test
    fun `homeviewmodel GetCities shows loading and then Cities states`() = runTest {
        coEvery { cityUsecase(any()) } coAnswers {
            delay(1L)
            Result.success(emptyList())
        }

        viewModel.onEvent(HomeEvent.GetCities)

        viewModel.homeUiState.test {
            assertThat(awaitItem()).isInstanceOf(HomeUiState.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(HomeUiState.Cities::class.java)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `homeviewmodel GetCities shows loading and then error when exception is thrown`() =
        runTest {

            coEvery { cityUsecase(any()) } coAnswers {
                delay(1L)
                Result.failure<List<City>>(Exception(""))
            }

            viewModel.onEvent(HomeEvent.GetCities)

            viewModel.homeUiState.test {
                assertThat(awaitItem()).isInstanceOf(HomeUiState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(HomeUiState.Error::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `homeviewmodel Search shows loading and then cities states`() =
        runTest {

            coEvery { cityUsecase(any()) } coAnswers {
                delay(1L)
                Result.success(emptyList())
            }

            viewModel.fetchCities()
            viewModel.onEvent(HomeEvent.Search("test"))

            viewModel.homeUiState.test {
                assertThat(awaitItem()).isInstanceOf(HomeUiState.Loading::class.java)
                assertThat(awaitItem()).isInstanceOf(HomeUiState.Cities::class.java)
                cancelAndIgnoreRemainingEvents()
            }
        }
}