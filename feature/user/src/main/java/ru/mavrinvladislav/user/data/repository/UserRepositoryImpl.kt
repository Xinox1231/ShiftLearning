package ru.mavrinvladislav.user.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import ru.mavrinvladislav.user.data.local.UserDao
import ru.mavrinvladislav.user.domain.model.User
import ru.mavrinvladislav.user.domain.repository.UserRepository

class UserRepositoryImpl(
    private val dao: UserDao
) : UserRepository {

    override fun getUsers(page: Int): Flow<List<User>> {
        val offset = PAGE_SIZE * (page - 1)
        val users = dao.getUsersPage(
            limit = PAGE_SIZE,
            offset = offset
        ).onEach { users ->
            if (users.isEmpty()){
                TODO("Грузим данные")
            }
        }
    }

    companion object {

        private const val PAGE_SIZE = 10

    }
}