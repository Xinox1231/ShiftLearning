package ru.mavrinvladislav.user.di

import kotlin.properties.Delegates.notNull

object UserDependenciesStore: UserDependenciesProvider {
    override val deps: UserDependencies by notNull()
}