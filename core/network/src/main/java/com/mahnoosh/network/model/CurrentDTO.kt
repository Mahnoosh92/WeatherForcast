package com.mahnoosh.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CurrentDTO(
    @SerialName("last_updated_epoch") val lastUpdatedEpoch: Int? = null,
    @SerialName("last_updated") val lastUpdated: String? = null,
    @SerialName("temp_c") val tempC: Double? = null,
    @SerialName("temp_f") val tempF: Double? = null,
    @SerialName("is_day") val isDay: Int? = null,
    @SerialName("condition") val condition: ConditionDTO = ConditionDTO(),
    @SerialName("wind_mph") val windMph: Double? = null,
    @SerialName("wind_kph") val windKph: Double? = null,
    @SerialName("wind_degree") val windDegree: Int? = null,
    @SerialName("wind_dir") val windDir: String? = null,
    @SerialName("pressure_mb") val pressureMb: Int? = null,
    @SerialName("pressure_in") val pressureIn: Double? = null,
    @SerialName("precip_mm") val precipMm: Int? = null,
    @SerialName("precip_in") val precipIn: Int? = null,
    @SerialName("humidity") val humidity: Int? = null,
    @SerialName("cloud") val cloud: Int? = null,
    @SerialName("feelslike_c") val feelslikeC: Double? = null,
    @SerialName("feelslike_f") val feelslikeF: Double? = null,
    @SerialName("windchill_c") val windchillC: Double? = null,
    @SerialName("windchill_f") val windchillF: Double? = null,
    @SerialName("heatindex_c") val heatindexC: Double? = null,
    @SerialName("heatindex_f") val heatindexF: Double? = null,
    @SerialName("dewpoint_c") val dewpointC: Double? = null,
    @SerialName("dewpoint_f") val dewpointF: Double? = null,
    @SerialName("vis_km") val visKm: Int? = null,
    @SerialName("vis_miles") val visMiles: Int? = null,
    @SerialName("uv") val uv: Int? = null,
    @SerialName("gust_mph") val gustMph: Double? = null,
    @SerialName("gust_kph") val gustKph: Double? = null,
    @SerialName("short_rad") val shortRad: Int? = null,
    @SerialName("diff_rad") val diffRad: Int? = null,
    @SerialName("dni") val dni: Int? = null,
    @SerialName("gti") val gti: Int? = null
)