package ru.mavrinvladislav.user.presentation.child.current_user

import kotlinx.coroutines.flow.StateFlow

interface CurrentUserComponent {

    val model: StateFlow<CurrentUserStore.State>

    fun onClickBack()
}