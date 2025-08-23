@file:OptIn(ExperimentalCoroutinesApi::class)

package ru.mavrinvladislav.user.presentation.child.users

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

class DefaultUsersComponent @AssistedInject constructor(
    private val storeFactory: UsersStoreFactory,
    @Assisted("componentContext") componentContext: ComponentContext
) : UsersComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create() }

    override val model: StateFlow<UsersStore.State> = store.stateFlow

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultUsersComponent
    }
}