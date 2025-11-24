package com.mahnoosh.network.data.builder

import com.mahnoosh.network.data.model.ConditionDTO

internal class ConditionDTOBuilder {
    private var text: String? = "Partly Cloudy"
    private var icon: String? = "//cdn.weatherapi.com/weather/64x64/day/116.png"
    private var code: Int? = 1003

    fun withText(text: String?) = apply { this.text = text }
    fun withIcon(icon: String?) = apply { this.icon = icon }
    fun withCode(code: Int?) = apply { this.code = code }

    fun build(): ConditionDTO {
        return ConditionDTO(
            text = text,
            icon = icon,
            code = code
        )
    }
}