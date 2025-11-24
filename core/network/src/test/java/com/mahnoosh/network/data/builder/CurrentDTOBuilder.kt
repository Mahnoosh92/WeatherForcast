package com.mahnoosh.network.data.builder

import com.mahnoosh.network.data.model.CurrentDTO

internal class CurrentDTOBuilder {
    private var lastUpdatedEpoch: Int? = 1700000000
    private var lastUpdated: String? = "2023-11-15 10:00"
    private var tempC: Double? = 15.5
    private var tempF: Double? = 59.9
    private var isDay: Int? = 1
    private var conditionBuilder: ConditionDTOBuilder = ConditionDTOBuilder()
    private var windMph: Double? = 5.0
    private var windKph: Double? = 8.05
    private var windDegree: Int? = 180
    private var windDir: String? = "S"
    private var pressureMb: Int? = 1015
    private var pressureIn: Double? = 29.97
    private var precipMm: Int? = 0
    private var precipIn: Int? = 0
    private var humidity: Int? = 60
    private var cloud: Int? = 0
    private var feelslikeC: Double? = 15.5
    private var feelslikeF: Double? = 59.9
    private var windchillC: Double? = 15.0
    private var windchillF: Double? = 59.0
    private var heatindexC: Double? = 16.0
    private var heatindexF: Double? = 60.8
    private var dewpointC: Double? = 8.0
    private var dewpointF: Double? = 46.4
    private var visKm: Int? = 10
    private var visMiles: Int? = 6
    private var uv: Int? = 5
    private var gustMph: Double? = 8.0
    private var gustKph: Double? = 12.87
    private var shortRad: Int? = 300
    private var diffRad: Int? = 50
    private var dni: Int? = 250
    private var gti: Int? = 350


    fun withLastUpdatedEpoch(lastUpdatedEpoch: Int?) = apply { this.lastUpdatedEpoch = lastUpdatedEpoch }
    fun withLastUpdated(lastUpdated: String?) = apply { this.lastUpdated = lastUpdated }
    fun withTempC(tempC: Double?) = apply { this.tempC = tempC }
    fun withTempF(tempF: Double?) = apply { this.tempF = tempF }
    fun withIsDay(isDay: Int?) = apply { this.isDay = isDay }
    // Allows setting the condition via a lambda builder
    fun withCondition(block: ConditionDTOBuilder.() -> Unit) = apply {
        this.conditionBuilder.apply(block)
    }
    fun withWindMph(windMph: Double?) = apply { this.windMph = windMph }
    fun withWindKph(windKph: Double?) = apply { this.windKph = windKph }
    fun withWindDegree(windDegree: Int?) = apply { this.windDegree = windDegree }
    fun withWindDir(windDir: String?) = apply { this.windDir = windDir }
    fun withPressureMb(pressureMb: Int?) = apply { this.pressureMb = pressureMb }
    fun withPressureIn(pressureIn: Double?) = apply { this.pressureIn = pressureIn }
    fun withPrecipMm(precipMm: Int?) = apply { this.precipMm = precipMm }
    fun withPrecipIn(precipIn: Int?) = apply { this.precipIn = precipIn }
    fun withHumidity(humidity: Int?) = apply { this.humidity = humidity }
    fun withCloud(cloud: Int?) = apply { this.cloud = cloud }
    fun withFeelslikeC(feelslikeC: Double?) = apply { this.feelslikeC = feelslikeC }
    fun withFeelslikeF(feelslikeF: Double?) = apply { this.feelslikeF = feelslikeF }
    fun withWindchillC(windchillC: Double?) = apply { this.windchillC = windchillC }
    fun withWindchillF(windchillF: Double?) = apply { this.windchillF = windchillF }
    fun withHeatindexC(heatindexC: Double?) = apply { this.heatindexC = heatindexC }
    fun withHeatindexF(heatindexF: Double?) = apply { this.heatindexF = heatindexF }
    fun withDewpointC(dewpointC: Double?) = apply { this.dewpointC = dewpointC }
    fun withDewpointF(dewpointF: Double?) = apply { this.dewpointF = dewpointF }
    fun withVisKm(visKm: Int?) = apply { this.visKm = visKm }
    fun withVisMiles(visMiles: Int?) = apply { this.visMiles = visMiles }
    fun withUv(uv: Int?) = apply { this.uv = uv }
    fun withGustMph(gustMph: Double?) = apply { this.gustMph = gustMph }
    fun withGustKph(gustKph: Double?) = apply { this.gustKph = gustKph }
    fun withShortRad(shortRad: Int?) = apply { this.shortRad = shortRad }
    fun withDiffRad(diffRad: Int?) = apply { this.diffRad = diffRad }
    fun withDni(dni: Int?) = apply { this.dni = dni }
    fun withGti(gti: Int?) = apply { this.gti = gti }

    fun build(): CurrentDTO {
        return CurrentDTO(
            lastUpdatedEpoch = lastUpdatedEpoch,
            lastUpdated = lastUpdated,
            tempC = tempC,
            tempF = tempF,
            isDay = isDay,
            condition = conditionBuilder.build(),
            windMph = windMph,
            windKph = windKph,
            windDegree = windDegree,
            windDir = windDir,
            pressureMb = pressureMb,
            pressureIn = pressureIn,
            precipMm = precipMm,
            precipIn = precipIn,
            humidity = humidity,
            cloud = cloud,
            feelslikeC = feelslikeC,
            feelslikeF = feelslikeF,
            windchillC = windchillC,
            windchillF = windchillF,
            heatindexC = heatindexC,
            heatindexF = heatindexF,
            dewpointC = dewpointC,
            dewpointF = dewpointF,
            visKm = visKm,
            visMiles = visMiles,
            uv = uv,
            gustMph = gustMph,
            gustKph = gustKph,
            shortRad = shortRad,
            diffRad = diffRad,
            dni = dni,
            gti = gti
        )
    }
}