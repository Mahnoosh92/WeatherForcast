package com.mahnoosh.data.datasource.remote.city

import com.mahnoosh.network.data.ApiService
import com.mahnoosh.network.data.errorhandler.SafeApiCaller
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher

import org.junit.Before

class DefaultCityRemoteDatasourceTest {

    private val apiService = mockk<ApiService>()
    private val safeApiCaller = mockk<SafeApiCaller>()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var cityRemoteDatasource: CityRemoteDatasource

    @Before
    fun setUp() {
        cityRemoteDatasource = DefaultCityRemoteDatasource(
            apiService = apiService,
            safeApiCaller = safeApiCaller,
            dispatcher = dispatcher
        )
    }


}