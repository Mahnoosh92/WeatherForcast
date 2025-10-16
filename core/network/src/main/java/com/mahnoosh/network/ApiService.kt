package com.mahnoosh.network

import com.mahnoosh.network.model.CityDTO
import com.mahnoosh.network.model.CurrentForcastDTO
import com.mahnoosh.network.model.CurrentWeatherDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("current.json")
    suspend fun fetchCurrentWeather(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") q: String
    ): Response<CurrentWeatherDTO>

    @GET("forecast.json")
    suspend fun fetchCurrentForcast(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") q: String,
        @Query("days") days: Int
    ): Response<CurrentForcastDTO>

    @GET("search.json")
    suspend fun fetchCities(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") q: String,
    ): Response<List<CityDTO>>
}