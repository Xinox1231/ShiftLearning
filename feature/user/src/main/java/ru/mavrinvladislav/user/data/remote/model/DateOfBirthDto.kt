package ru.mavrinvladislav.user.data.remote.model

import com.google.gson.annotations.SerializedName

data class DateOfBirthDto(
    @SerializedName("date") val date: String,
    @SerializedName("age") val age: String
)