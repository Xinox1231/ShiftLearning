@file:OptIn(ExperimentalCoroutinesApi::class)

package ru.mavrinvladislav.user.presentation.child.current_user

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

class DefaultCurrentUserComponent @AssistedInject constructor(
    private val currentUserStoreFactory: CurrentUserStoreFactory,
    @Assisted("userId") userId: Long,
    @Assisted("onBackClick") private val onBackClick: () -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : CurrentUserComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        currentUserStoreFactory.create(
            userId = userId
        )
    }

    override val model: StateFlow<CurrentUserStore.State> = store.stateFlow
    override fun onClickBack() = onBackClick()

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("userId") userId: Long,
            @Assisted("onBackClick") onBackClick: () -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultCurrentUserComponent
    }
}