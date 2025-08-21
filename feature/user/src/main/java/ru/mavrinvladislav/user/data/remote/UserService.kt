package ru.mavrinvladislav.user.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.mavrinvladislav.user.data.remote.model.UserResponse

interface UserService {

    @GET()
    fun fetchUsers(
        @Query("page") page: Int,
        @Query("results") pageSize: Int
    ): Response<UserResponse>
}