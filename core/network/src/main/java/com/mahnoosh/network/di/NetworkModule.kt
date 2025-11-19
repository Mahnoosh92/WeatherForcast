package com.mahnoosh.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mahnoosh.network.BuildConfig
import com.mahnoosh.network.data.ApiService
import com.mahnoosh.network.data.datasource.city.CitiesRemoteDatasource
import com.mahnoosh.network.data.datasource.city.DefaultCitiesRemoteDatasource
import com.mahnoosh.network.data.datasource.currennt.CurrentWeatherRemoteDatasource
import com.mahnoosh.network.data.datasource.currennt.DefaultCurrentWeatherRemoteDatasource
import com.mahnoosh.network.data.datasource.forcast.CurrentForcastRemoteDatasource
import com.mahnoosh.network.data.datasource.forcast.DefaultCurrentForcastRemoteDatasource
import com.mahnoosh.network.data.errorhandler.HttpErrorParser
import com.mahnoosh.network.data.errorhandler.SafeApiCaller
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    internal abstract fun bindsCurrentWeatherRemoteDatasource(
        defaultCurrentWeatherRemoteDatasource: DefaultCurrentWeatherRemoteDatasource,
    ): CurrentWeatherRemoteDatasource

    @Binds
    internal abstract fun bindsCurrentForcastRemoteDatasource(
        defaultCurrentForcastRemoteDatasource: DefaultCurrentForcastRemoteDatasource,
    ): CurrentForcastRemoteDatasource

    @Binds
    internal abstract fun bindsCitiesRemoteDatasource(
        defaultCitiesRemoteDatasource: DefaultCitiesRemoteDatasource,
    ): CitiesRemoteDatasource

    companion object {
        @Provides
        @Singleton
        fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        @Provides
        @Singleton
        fun provideGson(): Gson {
            return GsonBuilder().create()
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(
            loggingInterceptor: HttpLoggingInterceptor,
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
            val contentType = "application/json".toMediaType()
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        @Provides
        @Singleton
        fun provideHttpErrorParser(gson: Gson): HttpErrorParser = HttpErrorParser(gson)

        @Singleton
        @Provides
        fun provideApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }

        @Provides
        @Singleton
        fun provideSafeApiCaller(errorParser: HttpErrorParser): SafeApiCaller {
            return SafeApiCaller(errorParser)
        }
    }
}