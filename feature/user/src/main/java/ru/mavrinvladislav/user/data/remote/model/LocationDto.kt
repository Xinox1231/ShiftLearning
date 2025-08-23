package ru.mavrinvladislav.user.data.remote.model

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("street") val street: StreetDto,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
    @SerializedName("postcode") val postcode: String,
    @SerializedName("coordinates") val coordinates: CoordinatesDto,
    @SerializedName("timezone") val timezone: TimezoneDto
)
