package ru.mavrinvladislav.user.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mavrinvladislav.user.domain.model.User

interface UserRepository {

    suspend fun clearUsers()

    fun getUsers(): Flow<List<User>>

    suspend fun getUserById(id: Long): User
}