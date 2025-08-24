package ru.mavrinvladislav.user.ui.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import ru.mavrinvladislav.system_design.ui.compose.Screen
import ru.mavrinvladislav.user.presentation.root.UserChild
import ru.mavrinvladislav.user.presentation.root.UserRootComponent
import ru.mavrinvladislav.user.ui.child.current_user.CurrentUserContent
import ru.mavrinvladislav.user.ui.child.users.UsersContent

@Composable
fun UsersRootContent(component: UserRootComponent) {

    Screen() {
        Children(
            stack = component.stackNavigation,
            animation = stackAnimation(fade() + slide())
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
}