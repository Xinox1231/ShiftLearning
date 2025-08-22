package ru.mavrinvladislav.user.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import ru.mavrinvladislav.user.presentation.UserChild
import ru.mavrinvladislav.user.presentation.UserRootComponent
import ru.mavrinvladislav.user.ui.current_user.CurrentUserContent
import ru.mavrinvladislav.user.ui.users.UsersContent

@Composable
fun UsersRootContent(component: UserRootComponent) {

    Children(
        stack = component.stackNavigation
    ) {
        when (val instance = it.instance) {
            is UserChild.CurrentUser -> {
                CurrentUserContent(instance.component)
            }

            is UserChild.Users -> {
                UsersContent(instance.component)
            }
        }
    }
}