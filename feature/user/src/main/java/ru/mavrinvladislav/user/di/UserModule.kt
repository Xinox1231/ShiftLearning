package ru.mavrinvladislav.user.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.mavrinvladislav.user.data.remote.UserService
import ru.mavrinvladislav.user.data.repository.UserRepositoryImpl
import ru.mavrinvladislav.user.domain.repository.UserRepository

@Module
interface UserModule {

    @Binds
    @UserFeatureScope
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    companion object {

        @Provides
        @UserFeatureScope
        fun provideUserService(retrofit: Retrofit) = retrofit.create(UserService::class.java)

        @Provides
        @UserFeatureScope
        fun provideDefaultStoreFactory(): StoreFactory = DefaultStoreFactory()

    }
}