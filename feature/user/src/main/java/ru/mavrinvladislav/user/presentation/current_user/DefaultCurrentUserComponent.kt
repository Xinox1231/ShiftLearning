package ru.mavrinvladislav.user.presentation.current_user

import android.util.Log
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DefaultCurrentUserComponent @AssistedInject constructor(
) : CurrentUserComponent {

    init {
        Log.d("DefaultCurrentUserComponent", "init")
    }

    @AssistedFactory
    interface Factory {
        fun create(): DefaultCurrentUserComponent
    }
}