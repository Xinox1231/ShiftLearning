package ru.mavrinvladislav.user.presentation.child.users

import kotlinx.coroutines.flow.StateFlow

interface UsersComponent {

    val model: StateFlow<UsersStore.State>

    fun reloadPage()

    fun loadNewUsers()

    fun clickOnUser(userId: Long)

}