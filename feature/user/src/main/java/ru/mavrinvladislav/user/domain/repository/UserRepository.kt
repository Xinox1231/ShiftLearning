package ru.mavrinvladislav.user.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mavrinvladislav.user.domain.model.User

interface UserRepository {

    fun getUsers(
        page: Int,
    ): Flow<List<User>>
}