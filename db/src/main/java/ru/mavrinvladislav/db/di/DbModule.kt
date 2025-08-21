package ru.mavrinvladislav.db.di

import dagger.Module
import dagger.Provides
import ru.mavrinvladislav.db.AppDatabase
import ru.mavrinvladislav.db.datasource.UserLocalDataSource

@Module
interface DbModule {

    companion object {

        @Provides
        fun provideUserLocalDataSource(appDatabase: AppDatabase): UserLocalDataSource =
            appDatabase.userDao()
    }
}