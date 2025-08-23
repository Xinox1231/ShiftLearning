package ru.mavrinvladislav.user.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.mavrinvladislav.user.domain.model.User
import ru.mavrinvladislav.user.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
): (Int) -> Flow<List<User>> by repository::getUsers