package ru.mavrinvladislav.user.di

import dagger.Component
import ru.mavrinvladislav.user.presentation.child.users.UsersStore
import ru.mavrinvladislav.user.presentation.root.DefaultUserRootComponent

@Component(
    modules = [UserModule::class],
    dependencies = [UserDependencies::class]
)
@UserFeatureScope
interface UserComponent {

    fun getUserRootComponentFactory(): DefaultUserRootComponent.Factory

    @Component.Factory
    interface Factory {

        fun create(
            deps: UserDependencies
        ): UserComponent
    }
}