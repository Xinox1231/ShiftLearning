package ru.mavrinvladislav.user.domain.usecase

import ru.mavrinvladislav.user.domain.model.User
import ru.mavrinvladislav.user.domain.repository.UserRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val repository: UserRepository
) : suspend (Long) -> User by repository::getUserById