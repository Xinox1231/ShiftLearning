package ru.mavrinvladislav.user.di

import kotlin.properties.Delegates.notNull

object UserDependenciesStore : UserDependenciesProvider {
    override var dependencies: UserDependencies by notNull()
}