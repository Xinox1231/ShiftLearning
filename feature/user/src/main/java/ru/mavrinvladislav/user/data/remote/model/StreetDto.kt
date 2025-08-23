package ru.mavrinvladislav.user.data.remote.model

import com.google.gson.annotations.SerializedName

data class StreetDto(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String
)