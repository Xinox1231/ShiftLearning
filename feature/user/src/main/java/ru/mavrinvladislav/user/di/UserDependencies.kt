package ru.mavrinvladislav.user.di

import retrofit2.Retrofit
import ru.mavrinvladislav.db.datasource.UserLocalDataSource

interface UserDependencies {

    val retrofit: Retrofit
    val userLocalDataSource: UserLocalDataSource
}