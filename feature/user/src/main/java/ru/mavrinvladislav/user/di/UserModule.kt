package ru.mavrinvladislav.user.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.mavrinvladislav.db.AppDatabase
import ru.mavrinvladislav.user.data.remote.UserService
import ru.mavrinvladislav.user.data.repository.UserRepositoryImpl
import ru.mavrinvladislav.user.domain.repository.UserRepository

@Module
interface UserModule {

    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    companion object {

        @Provides
        fun provideUserService(retrofit: Retrofit) = retrofit.create(UserService::class.java)

        @Provides
        fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()
    }
}