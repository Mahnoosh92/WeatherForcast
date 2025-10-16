package com.mahnoosh.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayDTO(
    @SerialName("maxtemp_c") val maxtempC: Double? = null,
    @SerialName("maxtemp_f") val maxtempF: Int? = null,
    @SerialName("mintemp_c") val mintempC: Double? = null,
    @SerialName("mintemp_f") val mintempF: Double? = null,
    @SerialName("avgtemp_c") val avgtempC: Double? = null,
    @SerialName("avgtemp_f") val avgtempF: Double? = null,
    @SerialName("maxwind_mph") val maxwindMph: Double? = null,
    @SerialName("maxwind_kph") val maxwindKph: Double? = null,
    @SerialName("totalprecip_mm") val totalprecipMm: Int? = null,
    @SerialName("totalprecip_in") val totalprecipIn: Int? = null,
    @SerialName("totalsnow_cm") val totalsnowCm: Int? = null,
    @SerialName("avgvis_km") val avgvisKm: Int? = null,
    @SerialName("avgvis_miles") val avgvisMiles: Int? = null,
    @SerialName("avghumidity") val avghumidity: Int? = null,
    @SerialName("daily_will_it_rain") val dailyWillItRain: Int? = null,
    @SerialName("daily_chance_of_rain") val dailyChanceOfRain: Int? = null,
    @SerialName("daily_will_it_snow") val dailyWillItSnow: Int? = null,
    @SerialName("daily_chance_of_snow") val dailyChanceOfSnow: Int? = null,
    @SerialName("condition") val condition: ConditionDTO? = ConditionDTO(),
    @SerialName("uv") val uv: Double? = null
)