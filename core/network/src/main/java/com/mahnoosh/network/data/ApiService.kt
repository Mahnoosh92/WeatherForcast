package com.mahnoosh.network.data

import com.mahnoosh.network.BuildConfig
import com.mahnoosh.network.data.model.CityDTO
import com.mahnoosh.network.data.model.CurrentForcastDTO
import com.mahnoosh.network.data.model.CurrentWeatherDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") q: String
    ): Response<CurrentWeatherDTO>

    @GET("forecast.json")
    suspend fun getCurrentForcast(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") q: String,
        @Query("days") days: Int
    ): Response<CurrentForcastDTO>

    @GET("search.json")
    suspend fun getCities(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") q: String,
    ): Response<List<CityDTO>>
}