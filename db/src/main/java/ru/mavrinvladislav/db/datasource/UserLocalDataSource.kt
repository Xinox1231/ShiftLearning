package ru.mavrinvladislav.db.datasource

import kotlinx.coroutines.flow.Flow
import ru.mavrinvladislav.db.entity.UserDb

interface UserLocalDataSource {

    fun getUsersPage(limit: Int, offset: Int): Flow<List<UserDb>>
}