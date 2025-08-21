package ru.mavrinvladislav.shiftlearning

import android.app.Application
import ru.mavrinvladislav.shiftlearning.di.DaggerApplicationComponent
import kotlin.getValue

class App: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}