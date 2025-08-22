package ru.mavrinvladislav.user.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable
import ru.mavrinvladislav.user.di.DaggerUserComponent
import ru.mavrinvladislav.user.di.UserComponent
import ru.mavrinvladislav.user.di.UserDependencies
import ru.mavrinvladislav.user.presentation.current_user.DefaultCurrentUserComponent
import ru.mavrinvladislav.user.presentation.users.DefaultUsersComponent

class DefaultUserRootComponent @AssistedInject constructor(
    private val defaultUsersComponentFactory: DefaultUsersComponent.Factory,
    private val defaultCurrentUserComponentFactory: DefaultCurrentUserComponent.Factory,
    @Assisted("userDependencies") private val userDependencies: UserDependencies,
    @Assisted("componentContext") componentContext: ComponentContext
) : UserRootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<UserConfig>()

    override val stackNavigation: Value<ChildStack<*, UserChild>> = childStack(
        source = navigation,
        serializer = UserConfig.serializer(),
        initialConfiguration = UserConfig.Users,
        childFactory = ::child
    )

    override val userComponent: UserComponent =
        DaggerUserComponent.factory().create(userDependencies)

    fun child(config: UserConfig, componentContext: ComponentContext): UserChild {
        return when (config) {
            UserConfig.CurrentUser -> {
                val component = defaultCurrentUserComponentFactory.create()
                UserChild.CurrentUser(component)
            }

            UserConfig.Users -> {
                val component = defaultUsersComponentFactory.create()
                UserChild.Users(component)
            }
        }
    }

    @Serializable
    sealed interface UserConfig {

        @Serializable
        data object Users : UserConfig

        @Serializable
        data object CurrentUser : UserConfig
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("userDependencies") userDependencies: UserDependencies,
            @Assisted("componentContext") componentContext: ComponentContext
        )
    }
}