package com.mahnoosh.network.model


import com.google.gson.annotations.SerializedName

data class ConditionDTO(
    @SerializedName("text") val text: String?,
    @SerializedName("icon") val icon: String?,
    @SerializedName("code") val code: Int?
)