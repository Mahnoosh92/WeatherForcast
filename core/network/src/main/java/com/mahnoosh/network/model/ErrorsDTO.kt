package com.mahnoosh.network.model

import com.google.gson.annotations.SerializedName

data class ErrorsDTO(
    @SerializedName("error") var error: Error?
)