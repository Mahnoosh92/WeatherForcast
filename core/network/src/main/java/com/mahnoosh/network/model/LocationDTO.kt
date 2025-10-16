package com.mahnoosh.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDTO(
    @SerialName("name") val name: String? = null,
    @SerialName("region") val region: String? = null,
    @SerialName("country") val country: String? = null,
    @SerialName("lat") val lat: Double? = null,
    @SerialName("lon") val lon: Double? = null,
    @SerialName("tz_id") val tzId: String? = null,
    @SerialName("localtime_epoch") val localtimeEpoch: Int? = null,
    @SerialName("localtime") val localtime: String? = null

)