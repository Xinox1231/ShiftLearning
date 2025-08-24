package ru.mavrinvladislav.user.data.remote.model

import com.google.gson.annotations.SerializedName

internal data class CoordinatesDto(
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String
)