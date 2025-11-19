package com.mahnoosh.data.repository.city

import com.mahnoosh.data.datasource.remote.city.CityRemoteDatasource
import com.mahnoosh.model.data.City
import com.mahnoosh.threading.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DefaultCityRepository @Inject constructor(
    private val cityRemoteDatasource: CityRemoteDatasource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CityRepository {
    override suspend fun fetchCities(q: String): Result<List<City>> =
        cityRemoteDatasource.getCities(q = q)
}