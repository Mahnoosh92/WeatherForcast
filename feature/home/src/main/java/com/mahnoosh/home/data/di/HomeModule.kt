package com.mahnoosh.home.data.di

import com.mahnoosh.home.data.datasource.remote.city.CityRemoteDatasource
import com.mahnoosh.home.data.datasource.remote.city.DefaultCityRemoteDatasource
import com.mahnoosh.home.data.datasource.remote.forcast.DefaultForcastRemoteDatasource
import com.mahnoosh.home.data.datasource.remote.forcast.ForcastRemoteDatasource
import com.mahnoosh.home.data.datasource.remote.weather.DefaultWeatherRemoteDatasource
import com.mahnoosh.home.data.datasource.remote.weather.WeatherRemoteDatasource
import com.mahnoosh.home.data.repository.city.DefaultCityRepository
import com.mahnoosh.home.data.repository.forcast.DefaultForcastRepository
import com.mahnoosh.home.data.repository.weather.DefaultWeatherRepository
import com.mahnoosh.home.domain.repository.city.CityRepository
import com.mahnoosh.home.domain.repository.forcast.ForcastRepository
import com.mahnoosh.home.domain.repository.weather.WeatherRepository
import com.mahnoosh.home.domain.usecase.city.CityUsecase
import com.mahnoosh.home.domain.usecase.city.DefaultCityUsecase
import com.mahnoosh.home.domain.usecase.forcast.DefaultForcastUsecase
import com.mahnoosh.home.domain.usecase.forcast.ForcastUsecase
import com.mahnoosh.home.domain.usecase.weather.DefaultWeatherUsecase
import com.mahnoosh.home.domain.usecase.weather.WeatherUsecase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeModule {
    @Binds
    internal abstract fun bindWeatherRemoteDatasource(
        defaultWeatherRemoteDatasource: DefaultWeatherRemoteDatasource
    ): WeatherRemoteDatasource

    @Binds
    internal abstract fun bindForcastRemoteDatasource(
        defaultForcastRemoteDatasource: DefaultForcastRemoteDatasource
    ): ForcastRemoteDatasource

    @Binds
    internal abstract fun bindCityRemoteDatasource(
        defaultCityRemoteDatasource: DefaultCityRemoteDatasource
    ): CityRemoteDatasource

    @Binds
    internal abstract fun bindCityRepository(
        defaultCityRepository: DefaultCityRepository
    ): CityRepository

    @Binds
    internal abstract fun bindForcastRepository(
        defaultForcastRepository: DefaultForcastRepository
    ): ForcastRepository

    @Binds
    internal abstract fun bindWeatherRepository(
        defaultWeatherRepository: DefaultWeatherRepository
    ): WeatherRepository

    @Binds
    internal abstract fun bindCityUsecase(
        defaultCityUsecase: DefaultCityUsecase
    ): CityUsecase

    @Binds
    internal abstract fun bindForcastUsecase(
        defaultForcastUsecase: DefaultForcastUsecase
    ): ForcastUsecase

    @Binds
    internal abstract fun bindWeatherUsecase(
        defaultWeatherUsecase: DefaultWeatherUsecase
    ): WeatherUsecase
}