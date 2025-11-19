package com.mahnoosh.shared.builder

import com.mahnoosh.network.data.model.CityDTO

class CityDTOBuilder {
    private var id: Long = 1L
    private var name: String = "London"
    private var region: String = "England"
    private var country: String = "United Kingdom"
    private var lat: Double = 51.5074
    private var lon: Double = 0.1278
    private var url: String = "london-uk"

    fun withId(id: Long) = apply { this.id = id }
    fun withName(name: String) = apply { this.name = name }
    fun withRegion(region: String) = apply { this.region = region }
    fun withCountry(country: String) = apply { this.country = country }
    fun withLat(lat: Double) = apply { this.lat = lat }
    fun withLon(lon: Double) = apply { this.lon = lon }
    fun withUrl(url: String) = apply { this.url = url }

    fun build(): CityDTO {
        return CityDTO(
            id = id,
            name = name,
            region = region,
            country = country,
            lat = lat,
            lon = lon,
            url = url
        )
    }
}