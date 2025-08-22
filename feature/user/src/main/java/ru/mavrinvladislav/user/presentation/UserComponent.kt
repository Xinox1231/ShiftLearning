package ru.mavrinvladislav.user.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.mavrinvladislav.user.presentation.current_user.CurrentUserComponent

interface UserComponent {

    val stackNavigation: Value<ChildStack<*, UserChild>>

}

sealed interface UserChild{

    data class Users(val component: UserComponent): UserChild

    data class CurrentUser(val component: CurrentUserComponent): UserChild
}