package ru.mavrinvladislav.user.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.mavrinvladislav.user.data.remote.model.UserResponse

internal interface UserService {

    @GET(".")
    suspend fun fetchUsers(
        @Query("results") pageSize: Int
    ): UserResponse
}