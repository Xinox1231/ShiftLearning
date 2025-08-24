package ru.mavrinvladislav.user.domain.usecase

import ru.mavrinvladislav.user.domain.repository.UserRepository
import javax.inject.Inject

class ClearUsersUseCase @Inject constructor(
    private val repository: UserRepository
) : suspend () -> Unit by repository::clearUsers