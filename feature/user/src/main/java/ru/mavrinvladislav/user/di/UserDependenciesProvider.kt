package ru.mavrinvladislav.user.di

interface UserDependenciesProvider {
    val deps: UserDependencies

    companion object : UserDependenciesProvider by UserDependenciesStore
}