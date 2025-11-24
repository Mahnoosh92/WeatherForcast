package com.mahnoosh.network.data.builder

import com.mahnoosh.network.data.model.LocationDTO

internal class LocationDTOBuilder {
    private var name: String? = "London"
    private var region: String? = "City of London, Greater London"
    private var country: String? = "United Kingdom"
    private var lat: Double? = 51.52
    private var lon: Double? = -0.11
    private var tzId: String? = "Europe/London"
    private var localtimeEpoch: Int? = 1700000000
    private var localtime: String? = "2023-11-15 10:00"

    fun withName(name: String?) = apply { this.name = name }
    fun withRegion(region: String?) = apply { this.region = region }
    fun withCountry(country: String?) = apply { this.country = country }
    fun withLat(lat: Double?) = apply { this.lat = lat }
    fun withLon(lon: Double?) = apply { this.lon = lon }
    fun withTzId(tzId: String?) = apply { this.tzId = tzId }
    fun withLocaltimeEpoch(localtimeEpoch: Int?) = apply { this.localtimeEpoch = localtimeEpoch }
    fun withLocaltime(localtime: String?) = apply { this.localtime = localtime }

    fun build(): LocationDTO {
        return LocationDTO(
            name = name,
            region = region,
            country = country,
            lat = lat,
            lon = lon,
            tzId = tzId,
            localtimeEpoch = localtimeEpoch,
            localtime = localtime
        )
    }
}