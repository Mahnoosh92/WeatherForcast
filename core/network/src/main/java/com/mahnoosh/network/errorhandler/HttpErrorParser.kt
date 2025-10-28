package com.mahnoosh.network.errorhandler

import android.util.Log
import com.google.gson.Gson
import com.mahnoosh.network.model.ErrorsDTO
import javax.inject.Inject

class HttpErrorParser @Inject constructor(
    private val gson: Gson
) {
    companion object {
        private const val TAG = "HttpErrorParser"
    }

    fun parseGenericError(errorBody: String): ErrorsDTO? {
        return try {
            gson.fromJson(errorBody, ErrorsDTO::class.java)
        } catch (e: Exception) {
            Log.e(TAG, "parseGenericError: ${e.message}")
            null
        }
    }
}