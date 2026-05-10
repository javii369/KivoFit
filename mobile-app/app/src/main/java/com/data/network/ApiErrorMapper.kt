package com.KivoFit.data.network

import com.KivoFit.data.network.dto.ErrorResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.HttpException

object ApiErrorMapper {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun message(exception: HttpException): String {
        val body = exception.response()?.errorBody()?.string() ?: return "Error ${exception.code()}"
        return try {
            val err = moshi.adapter(ErrorResponse::class.java).fromJson(body)
            err?.errors?.values?.flatten()?.firstOrNull()
                ?: err?.message
                ?: err?.error
                ?: "Error ${exception.code()}"
        } catch (_: Exception) {
            "Error ${exception.code()}"
        }
    }
}
