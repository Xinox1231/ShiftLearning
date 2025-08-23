package ru.mavrinvladislav.user.di

interface UserDependenciesProvider {
    val dependencies: UserDependencies

    companion object: UserDependenciesProvider by UserDependenciesStore
}