package ru.mavrinvladislav.user.di

import dagger.Component

@Component(
    modules = [UserModule::class],
    dependencies = []
)
interface UserComponent {

    @Component.Factory
    interface Factory{

        fun create()
    }
}