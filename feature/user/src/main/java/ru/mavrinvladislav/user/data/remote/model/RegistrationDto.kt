package ru.mavrinvladislav.user.data.remote.model

import com.google.gson.annotations.SerializedName

internal data class RegistrationDto(
    @SerializedName("date") val date: String,
    @SerializedName("age")val yearsSinceRegistered: Int
)