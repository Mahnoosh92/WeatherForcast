package com.mahnoosh.network.data.builder

import com.mahnoosh.network.data.model.DayDTO

internal class DayDTOBuilder {
    private var maxtempC: Double? = 20.0
    private var maxtempF: Int? = 68
    private var mintempC: Double? = 10.0
    private var mintempF: Double? = 50.0
    private var avgtempC: Double? = 15.0
    private var avgtempF: Double? = 59.0
    private var maxwindMph: Double? = 10.0
    private var maxwindKph: Double? = 16.1
    private var totalprecipMm: Int? = 0
    private var totalprecipIn: Int? = 0
    private var totalsnowCm: Int? = 0
    private var avgvisKm: Int? = 10
    private var avgvisMiles: Int? = 6
    private var avghumidity: Int? = 65
    private var dailyWillItRain: Int? = 0
    private var dailyChanceOfRain: Int? = 0
    private var dailyWillItSnow: Int? = 0
    private var dailyChanceOfSnow: Int? = 0
    private var conditionBuilder: ConditionDTOBuilder = ConditionDTOBuilder()
    private var uv: Double? = 5.0

    fun withMaxtempC(maxtempC: Double?) = apply { this.maxtempC = maxtempC }
    fun withMaxtempF(maxtempF: Int?) = apply { this.maxtempF = maxtempF }
    fun withMintempC(mintempC: Double?) = apply { this.mintempC = mintempC }
    fun withMintempF(mintempF: Double?) = apply { this.mintempF = mintempF }
    fun withAvgtempC(avgtempC: Double?) = apply { this.avgtempC = avgtempC }
    fun withAvgtempF(avgtempF: Double?) = apply { this.avgtempF = avgtempF }
    fun withMaxwindMph(maxwindMph: Double?) = apply { this.maxwindMph = maxwindMph }
    fun withMaxwindKph(maxwindKph: Double?) = apply { this.maxwindKph = maxwindKph }
    fun withTotalprecipMm(totalprecipMm: Int?) = apply { this.totalprecipMm = totalprecipMm }
    fun withTotalprecipIn(totalprecipIn: Int?) = apply { this.totalprecipIn = totalprecipIn }
    fun withTotalsnowCm(totalsnowCm: Int?) = apply { this.totalsnowCm = totalsnowCm }
    fun withAvgvisKm(avgvisKm: Int?) = apply { this.avgvisKm = avgvisKm }
    fun withAvgvisMiles(avgvisMiles: Int?) = apply { this.avgvisMiles = avgvisMiles }
    fun withAvghumidity(avghumidity: Int?) = apply { this.avghumidity = avghumidity }
    fun withDailyWillItRain(dailyWillItRain: Int?) =
        apply { this.dailyWillItRain = dailyWillItRain }

    fun withDailyChanceOfRain(dailyChanceOfRain: Int?) =
        apply { this.dailyChanceOfRain = dailyChanceOfRain }

    fun withDailyWillItSnow(dailyWillItSnow: Int?) =
        apply { this.dailyWillItSnow = dailyWillItSnow }

    fun withDailyChanceOfSnow(dailyChanceOfSnow: Int?) =
        apply { this.dailyChanceOfSnow = dailyChanceOfSnow }

    fun withCondition(block: ConditionDTOBuilder.() -> Unit) =
        apply { this.conditionBuilder.apply(block) }

    fun withUv(uv: Double?) = apply { this.uv = uv }

    fun build(): DayDTO {
        return DayDTO(
            maxtempC = maxtempC,
            maxtempF = maxtempF,
            mintempC = mintempC,
            mintempF = mintempF,
            avgtempC = avgtempC,
            avgtempF = avgtempF,
            maxwindMph = maxwindMph,
            maxwindKph = maxwindKph,
            totalprecipMm = totalprecipMm,
            totalprecipIn = totalprecipIn,
            totalsnowCm = totalsnowCm,
            avgvisKm = avgvisKm,
            avgvisMiles = avgvisMiles,
            avghumidity = avghumidity,
            dailyWillItRain = dailyWillItRain,
            dailyChanceOfRain = dailyChanceOfRain,
            dailyWillItSnow = dailyWillItSnow,
            dailyChanceOfSnow = dailyChanceOfSnow,
            condition = conditionBuilder.build(),
            uv = uv
        )
    }
}