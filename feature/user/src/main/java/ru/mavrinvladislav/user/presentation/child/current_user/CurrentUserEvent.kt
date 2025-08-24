package ru.mavrinvladislav.user.presentation.child.current_user

import ru.mavrinvladislav.user.domain.model.Coordinates

sealed interface CurrentUserEvent {

    data class OpenDialer(val phone: String) : CurrentUserEvent

    data class OpenEmail(val email: String) : CurrentUserEvent

    data class OpenMap(val coordinates: Coordinates) : CurrentUserEvent
}