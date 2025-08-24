package ru.mavrinvladislav.user.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import ru.mavrinvladislav.db.datasource.UserLocalDataSource
import ru.mavrinvladislav.db.entity.UserDb
import ru.mavrinvladislav.user.data.remote.UserService
import ru.mavrinvladislav.user.data.toDomain
import ru.mavrinvladislav.user.data.toEntity
import ru.mavrinvladislav.user.domain.model.User
import ru.mavrinvladislav.user.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserService,
    private val dao: UserLocalDataSource
) : UserRepository {

    override suspend fun clearUsers() {
        dao.clearUsers()
    }

    override fun getUsers(): Flow<List<User>> {
        return dao.getUsers()
            .onEach { users ->
                if (users.isEmpty()) {
                    val users = loadUsers()
                    addUsersToLocalDataSource(users)
                }
            }.map { list ->
                list.map { it.toDomain() }
            }
    }

    override suspend fun getUserById(id: Long): User = dao.getUser(id).toDomain()

    private suspend fun addUsersToLocalDataSource(users: List<UserDb>) {
        dao.addUsers(users)
    }

    private suspend fun loadUsers(): List<UserDb> {
        return api.fetchUsers(PAGE_SIZE).users.map {
            it.toEntity()
        }
    }

    companion object {
        private const val PAGE_SIZE = 100
    }
}