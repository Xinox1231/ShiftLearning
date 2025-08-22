package ru.mavrinvladislav.shiftlearning.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.mavrinvladislav.db.di.DbModule
import ru.mavrinvladislav.di.scopes.ApplicationScope
import ru.mavrinvladislav.network.NetworkModule
import ru.mavrinvladislav.shiftlearning.MainActivity
import ru.mavrinvladislav.user.di.UserDependencies

@ApplicationScope
@Component(
    modules = [
        DbModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent : UserDependencies {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}