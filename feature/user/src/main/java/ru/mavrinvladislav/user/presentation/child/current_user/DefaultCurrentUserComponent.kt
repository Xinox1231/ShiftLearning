@file:OptIn(ExperimentalCoroutinesApi::class)

package ru.mavrinvladislav.user.presentation.child.current_user

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import ru.mavrinvladislav.decompose.componentScope
import ru.mavrinvladislav.user.presentation.child.current_user.CurrentUserEvent.*

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
    private val scope = componentScope()
    private val _event = Channel<CurrentUserEvent>(Channel.BUFFERED)
    override val event: Flow<CurrentUserEvent>
        get() = _event.receiveAsFlow()

    init {
        store.labels.onEach {
            when (it) {
                is CurrentUserStore.Label.OnPhoneClicked -> {
                    _event.send(OpenDialer(it.phone))
                }

                is CurrentUserStore.Label.OnEmailClicked -> {
                    _event.send(OpenEmail(it.email))
                }

                is CurrentUserStore.Label.OnCoordinatesClicked -> {
                    _event.send(OpenMap(it.coordinates))
                }

            }
        }.launchIn(scope)
    }

    override val model: StateFlow<CurrentUserStore.State> = store.stateFlow

    override fun onPhoneClick() {
        store.accept(CurrentUserStore.Intent.OnPhoneClicked)
    }

    override fun onEmailClick() {
        store.accept(CurrentUserStore.Intent.OnEmailClicked)
    }

    override fun onCoordinatesClick() {
        store.accept(CurrentUserStore.Intent.OnCoordinatesClicked)
    }

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