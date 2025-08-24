package ru.mavrinvladislav.user.data.remote.datasource

import ru.mavrinvladislav.user.data.remote.model.UserResponse

internal interface UserRemoteDataSource {
    suspend fun fetchUsers(pageSize: Int): UserResponse
}