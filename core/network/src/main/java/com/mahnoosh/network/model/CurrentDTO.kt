package com.mahnoosh.network.model

import com.google.gson.annotations.SerializedName


data class CurrentDTO(
    @SerializedName("last_updated_epoch") val lastUpdatedEpoch: Int?,
    @SerializedName("last_updated") val lastUpdated: String?,
    @SerializedName("temp_c") val tempC: Double?,
    @SerializedName("temp_f") val tempF: Double?,
    @SerializedName("is_day") val isDay: Int?,
    @SerializedName("condition") val condition: ConditionDTO?,
    @SerializedName("wind_mph") val windMph: Double?,
    @SerializedName("wind_kph") val windKph: Double?,
    @SerializedName("wind_degree") val windDegree: Int?,
    @SerializedName("wind_dir") val windDir: String?,
    @SerializedName("pressure_mb") val pressureMb: Int?,
    @SerializedName("pressure_in") val pressureIn: Double?,
    @SerializedName("precip_mm") val precipMm: Int?,
    @SerializedName("precip_in") val precipIn: Int?,
    @SerializedName("humidity") val humidity: Int?,
    @SerializedName("cloud") val cloud: Int?,
    @SerializedName("feelslike_c") val feelslikeC: Double?,
    @SerializedName("feelslike_f") val feelslikeF: Double?,
    @SerializedName("windchill_c") val windchillC: Double?,
    @SerializedName("windchill_f") val windchillF: Double?,
    @SerializedName("heatindex_c") val heatindexC: Double?,
    @SerializedName("heatindex_f") val heatindexF: Double?,
    @SerializedName("dewpoint_c") val dewpointC: Double?,
    @SerializedName("dewpoint_f") val dewpointF: Double?,
    @SerializedName("vis_km") val visKm: Int? = null,
    @SerializedName("vis_miles") val visMiles: Int?,
    @SerializedName("uv") val uv: Int?,
    @SerializedName("gust_mph") val gustMph: Double?,
    @SerializedName("gust_kph") val gustKph: Double?,
    @SerializedName("short_rad") val shortRad: Int?,
    @SerializedName("diff_rad") val diffRad: Int?,
    @SerializedName("dni") val dni: Int?,
    @SerializedName("gti") val gti: Int?
)