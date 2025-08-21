package ru.mavrinvladislav.db.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.mavrinvladislav.db.AppDatabase
import ru.mavrinvladislav.db.datasource.UserLocalDataSource
import ru.mavrinvladislav.di.scopes.ApplicationScope


@ApplicationScope
@Module
class DbModule {

    @ApplicationScope
    @Provides
    fun provideDb(context: Context) = AppDatabase.getInstance(context)

    @Provides
    fun provideUserLocalDataSource(appDatabase: AppDatabase): UserLocalDataSource =
        appDatabase.userDao()
}