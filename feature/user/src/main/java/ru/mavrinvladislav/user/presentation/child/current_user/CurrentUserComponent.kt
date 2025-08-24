package ru.mavrinvladislav.user.presentation.child.current_user

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface CurrentUserComponent {

    val model: StateFlow<CurrentUserStore.State>
    val event: Flow<CurrentUserEvent>

    fun onPhoneClick()

    fun onEmailClick()

    fun onCoordinatesClick()

    fun onClickBack()
}