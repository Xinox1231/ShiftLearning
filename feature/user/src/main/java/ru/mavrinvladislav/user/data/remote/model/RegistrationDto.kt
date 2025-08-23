package ru.mavrinvladislav.user.data.remote.model

import com.google.gson.annotations.SerializedName

data class RegistrationDto(
    @SerializedName("date") val date: String,
    @SerializedName("age")val yearsSinceRegistered: Int
)