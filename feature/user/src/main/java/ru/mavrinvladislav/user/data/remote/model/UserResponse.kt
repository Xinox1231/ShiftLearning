package ru.mavrinvladislav.user.data.remote.model

import com.google.gson.annotations.SerializedName

internal data class UserResponse(
    @SerializedName("results") val users: List<UserDto>
)