@file:OptIn(ExperimentalCoroutinesApi::class)

package ru.mavrinvladislav.user.presentation.child.users

import android.util.Log
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
    @Assisted("componentContext") componentContext: ComponentContext,
    @Assisted("onUserClicked") private val onUserClicked: (Long) -> Unit
) : UsersComponent, ComponentContext by componentContext {
    private val store = instanceKeeper.getStore { storeFactory.create() }
    override val model: StateFlow<UsersStore.State> = store.stateFlow

    override fun reloadPage() {
        store.accept(UsersStore.Intent.ReloadPage)
    }

    override fun loadNewUsers() {
        store.accept(UsersStore.Intent.LoadNewUsers)
    }

    override fun clickOnUser(userId: Long) {
        onUserClicked(userId)
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext,
            @Assisted("onUserClicked") onUserClick: (Long) -> Unit
        ): DefaultUsersComponent
    }
}