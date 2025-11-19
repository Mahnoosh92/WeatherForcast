package com.mahnoosh.network.data.model

import com.google.gson.annotations.SerializedName

data class ErrorsDTO(
    @SerializedName("error") var error: ErrorDTO?
)