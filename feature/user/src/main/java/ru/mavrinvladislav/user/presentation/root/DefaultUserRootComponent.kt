package ru.mavrinvladislav.user.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable
import ru.mavrinvladislav.user.di.DaggerUserComponent
import ru.mavrinvladislav.user.di.UserComponent
import ru.mavrinvladislav.user.di.UserDependencies
import ru.mavrinvladislav.user.presentation.child.current_user.DefaultCurrentUserComponent
import ru.mavrinvladislav.user.presentation.child.users.DefaultUsersComponent

class DefaultUserRootComponent @AssistedInject constructor(
    private val defaultUsersComponentFactory: DefaultUsersComponent.Factory,
    private val defaultCurrentUserComponentFactory: DefaultCurrentUserComponent.Factory,
    @Assisted("componentContext") componentContext: ComponentContext
) : UserRootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<UserConfig>()

    override val stackNavigation: Value<ChildStack<*, UserChild>> = childStack(
        source = navigation,
        serializer = UserConfig.serializer(),
        handleBackButton = true,
        initialConfiguration = UserConfig.Users,
        childFactory = ::child
    )

    fun child(config: UserConfig, componentContext: ComponentContext): UserChild {
        return when (config) {
            is UserConfig.CurrentUser -> {
                val component = defaultCurrentUserComponentFactory.create(
                    userId = config.userId,
                    onBackClick = { navigation.pop() },
                    componentContext = componentContext
                )
                UserChild.CurrentUser(component)
            }

            is UserConfig.Users -> {
                val component = defaultUsersComponentFactory.create(
                    componentContext = componentContext,
                    onUserClick = {
                        navigation.push(
                            UserConfig.CurrentUser(
                                userId = it
                            )
                        )
                    }
                )
                UserChild.Users(component)
            }
        }
    }

    @Serializable
    sealed class UserConfig {

        @Serializable
        data object Users : UserConfig()

        @Serializable
        data class CurrentUser(val userId: Long) : UserConfig()
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultUserRootComponent
    }
}