package ru.mavrinvladislav.db.datasource

import kotlinx.coroutines.flow.Flow
import ru.mavrinvladislav.db.entity.UserDb

interface UserLocalDataSource {

    fun getUsers(): Flow<List<UserDb>>

    suspend fun getUser(id: Long): UserDb

    suspend fun clearUsers()
    suspend fun addUsers(users: List<UserDb>)
}