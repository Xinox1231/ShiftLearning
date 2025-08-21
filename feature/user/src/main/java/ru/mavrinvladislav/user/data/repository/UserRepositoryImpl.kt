package ru.mavrinvladislav.user.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import ru.mavrinvladislav.db.datasource.UserLocalDataSource
import ru.mavrinvladislav.user.data.remote.UserService
import ru.mavrinvladislav.user.data.toDomain
import ru.mavrinvladislav.user.domain.model.User
import ru.mavrinvladislav.user.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserService,
    private val dao: UserLocalDataSource
) : UserRepository {

    override fun getUsers(page: Int): Flow<List<User>> {
        val offset = PAGE_SIZE * (page - 1)
        return dao.getUsersPage(
            limit = PAGE_SIZE,
            offset = offset
        ).onEach { users ->
            if (users.isEmpty()) {
                val usersFromNetwork = api.fetchUsers(
                    page = page,
                    pageSize = PAGE_SIZE
                ).body()
            }
        }.map { users ->
            users.map {
                it.toDomain()
            }
        }
    }

    companion object {

        private const val PAGE_SIZE = 10

    }
}