package com.mahnoosh.network.data.builder

import com.mahnoosh.network.data.model.ForecastDTO
import com.mahnoosh.network.data.model.ForecastDayDTO

internal class ForecastDTOBuilder {
    private var forecastdays: List<ForecastDayDTO?>? = listOf(ForecastDayDTOBuilder().build())

    fun withForecastdays(days: List<ForecastDayDTO?>?) = apply { this.forecastdays = days }
    fun withSingleDay(block: ForecastDayDTOBuilder.() -> Unit) = apply {
        this.forecastdays = listOf(ForecastDayDTOBuilder().apply(block).build())
    }

    fun build(): ForecastDTO {
        return ForecastDTO(forecastday = forecastdays)
    }
}