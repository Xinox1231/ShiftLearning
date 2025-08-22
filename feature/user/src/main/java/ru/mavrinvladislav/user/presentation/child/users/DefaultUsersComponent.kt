package ru.mavrinvladislav.user.presentation.child.users

import android.util.Log
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DefaultUsersComponent @AssistedInject constructor() : UsersComponent {

    init {
        Log.d("DefaultUsersComponent", "init")
    }

    @AssistedFactory
    interface Factory {
        fun create(): DefaultUsersComponent
    }
}