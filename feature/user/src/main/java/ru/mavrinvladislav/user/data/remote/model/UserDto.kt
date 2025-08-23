package ru.mavrinvladislav.user.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("gender") val gender: String,
    @SerializedName("name") val name: NameDto,
    @SerializedName("location") val location: LocationDto,
    @SerializedName("email") val email: String,
    @SerializedName("dob") val dateOfBirth: DateOfBirthDto,
    @SerializedName("registered") val registered: RegistrationDto,
    @SerializedName("phone") val phone: String,
    @SerializedName("cell") val cell: String,
    @SerializedName("picture") val picture: PictureDto,
    @SerializedName("nat") val nat: String
)