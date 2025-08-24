package ru.mavrinvladislav.db.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.mavrinvladislav.db.AppDatabase
import ru.mavrinvladislav.db.datasource.UserLocalDataSource
import ru.mavrinvladislav.di.scopes.ApplicationScope

@Module
class DbModule {

    @ApplicationScope
    @Provides
    internal fun provideDb(context: Context) = AppDatabase.getInstance(context)

    @Provides
    internal fun provideUserLocalDataSource(appDatabase: AppDatabase): UserLocalDataSource =
        appDatabase.userDao()
}