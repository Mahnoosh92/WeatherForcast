package com.mahnoosh.network.data.builder

import com.mahnoosh.network.data.model.ForecastDayDTO

internal class ForecastDayDTOBuilder {
    private var date: String? = "2024-11-25"
    private var dateEpoch: Int? = 1700000000
    private var dayBuilder: DayDTOBuilder = DayDTOBuilder()
    private var astroBuilder: AstroDTOBuilder = AstroDTOBuilder()

    // Default hour list has two builders for 12:00 and 18:00
    private var hourBuilders: List<HourDTOBuilder> = listOf(
        HourDTOBuilder().withTime("2024-11-25 12:00").withTempC(15.0),
        HourDTOBuilder().withTime("2024-11-25 18:00").withTempC(10.0)
    )

    fun withDate(date: String?) = apply { this.date = date }
    fun withDateEpoch(dateEpoch: Int?) = apply { this.dateEpoch = dateEpoch }
    fun withDay(block: DayDTOBuilder.() -> Unit) = apply { this.dayBuilder.apply(block) }
    fun withAstro(block: AstroDTOBuilder.() -> Unit) = apply { this.astroBuilder.apply(block) }

    fun withHourList(blocks: List<HourDTOBuilder.() -> Unit>) = apply {
        this.hourBuilders = blocks.map { HourDTOBuilder().apply(it) }
    }

    fun build(): ForecastDayDTO {
        return ForecastDayDTO(
            date = date,
            dateEpoch = dateEpoch,
            day = dayBuilder.build(),
            astro = astroBuilder.build(),
            hour = hourBuilders.map { it.build() }
        )
    }
}