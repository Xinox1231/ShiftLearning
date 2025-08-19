package ru.mavrinvladislav.user.domain.repository

import ru.mavrinvladislav.user.domain.model.User

interface UserRepository {

    fun getUsers(
        page: Int,
    ): List<User>
}