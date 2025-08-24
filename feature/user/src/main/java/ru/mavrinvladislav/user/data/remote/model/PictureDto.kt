package ru.mavrinvladislav.user.data.remote.model

import com.google.gson.annotations.SerializedName

internal data class PictureDto(
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("thumbnail") val thumbnail: String
)