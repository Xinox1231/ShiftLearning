package ru.mavrinvladislav.user.data.remote.model

import com.google.gson.annotations.SerializedName

internal data class TimezoneDto(
    @SerializedName("offset") val offset: String,
    @SerializedName("description") val description: String
)