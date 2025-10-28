package com.mahnoosh.network.model

import com.google.gson.annotations.SerializedName

data class ErrorDTO(
    @SerializedName("code") var code: Int?,
    @SerializedName("message") var message: String?
)
