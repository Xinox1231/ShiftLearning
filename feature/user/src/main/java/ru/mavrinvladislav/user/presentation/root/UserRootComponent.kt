package ru.mavrinvladislav.user.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.mavrinvladislav.user.presentation.child.current_user.CurrentUserComponent
import ru.mavrinvladislav.user.presentation.child.users.UsersComponent
import ru.mavrinvladislav.user.presentation.child.users.UsersStore.Intent

interface UserRootComponent {

    val stackNavigation: Value<ChildStack<*, UserChild>>

}

sealed interface UserChild{

    data class Users(val component: UsersComponent): UserChild

    data class CurrentUser(val component: CurrentUserComponent): UserChild
}