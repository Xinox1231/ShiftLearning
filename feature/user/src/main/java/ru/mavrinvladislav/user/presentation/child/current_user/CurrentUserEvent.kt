package ru.mavrinvladislav.user.presentation.child.current_user

sealed interface CurrentUserEvent {

    data class OpenDialer(val phone: String): CurrentUserEvent
}