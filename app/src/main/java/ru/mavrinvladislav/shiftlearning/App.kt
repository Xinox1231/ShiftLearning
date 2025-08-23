package ru.mavrinvladislav.shiftlearning

import android.app.Application
import ru.mavrinvladislav.shiftlearning.di.DaggerApplicationComponent
import ru.mavrinvladislav.user.di.UserDependenciesStore

class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        UserDependenciesStore.dependencies = component
    }
}