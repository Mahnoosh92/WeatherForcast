package com.mahnoosh.network.model

import com.google.gson.annotations.SerializedName

data class DayDTO(
    @SerializedName("maxtemp_c") val maxtempC: Double?,
    @SerializedName("maxtemp_f") val maxtempF: Int?,
    @SerializedName("mintemp_c") val mintempC: Double?,
    @SerializedName("mintemp_f") val mintempF: Double?,
    @SerializedName("avgtemp_c") val avgtempC: Double?,
    @SerializedName("avgtemp_f") val avgtempF: Double?,
    @SerializedName("maxwind_mph") val maxwindMph: Double?,
    @SerializedName("maxwind_kph") val maxwindKph: Double?,
    @SerializedName("totalprecip_mm") val totalprecipMm: Int?,
    @SerializedName("totalprecip_in") val totalprecipIn: Int?,
    @SerializedName("totalsnow_cm") val totalsnowCm: Int?,
    @SerializedName("avgvis_km") val avgvisKm: Int?,
    @SerializedName("avgvis_miles") val avgvisMiles: Int?,
    @SerializedName("avghumidity") val avghumidity: Int?,
    @SerializedName("daily_will_it_rain") val dailyWillItRain: Int?,
    @SerializedName("daily_chance_of_rain") val dailyChanceOfRain: Int?,
    @SerializedName("daily_will_it_snow") val dailyWillItSnow: Int?,
    @SerializedName("daily_chance_of_snow") val dailyChanceOfSnow: Int?,
    @SerializedName("condition") val condition: ConditionDTO?,
    @SerializedName("uv") val uv: Double?
)