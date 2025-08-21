package ru.mavrinvladislav.shiftlearning.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.mavrinvladislav.db.di.DbModule
import ru.mavrinvladislav.di.scopes.ApplicationScope

@ApplicationScope
@Component(
    modules = [DbModule::class]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}