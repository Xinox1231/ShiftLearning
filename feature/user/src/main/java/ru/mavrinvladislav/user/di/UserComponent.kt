package ru.mavrinvladislav.user.di

import dagger.Component

@Component(
    modules = [UserModule::class],
    dependencies = [UserDependencies::class]
)
internal interface UserComponent {

    @Component.Factory
    interface Factory {

        fun create(deps: UserDependencies): UserComponent
    }
}