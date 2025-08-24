package ru.mavrinvladislav.user.presentation.child.current_user

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.launch
import ru.mavrinvladislav.user.domain.model.Coordinates
import ru.mavrinvladislav.user.domain.model.User
import ru.mavrinvladislav.user.domain.usecase.GetCurrentUserUseCase
import ru.mavrinvladislav.user.presentation.child.current_user.CurrentUserStore.Intent
import ru.mavrinvladislav.user.presentation.child.current_user.CurrentUserStore.Label
import ru.mavrinvladislav.user.presentation.child.current_user.CurrentUserStore.Label.*
import ru.mavrinvladislav.user.presentation.child.current_user.CurrentUserStore.State
import ru.mavrinvladislav.user.presentation.child.users.UsersStore
import javax.inject.Inject

interface CurrentUserStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data object OnPhoneClicked : Intent
        data object OnEmailClicked : Intent
        data object OnCoordinatesClicked : Intent
    }

    data class State(
        val state: UserState
    ) {
        sealed interface UserState {
            data object Initial : UserState

            data class Loaded(
                val user: User
            ) : UserState
        }
    }

    sealed interface Label {
        data class OnPhoneClicked(val phone: String) : Label
        data class OnEmailClicked(val email: String) : Label
        data class OnCoordinatesClicked(val coordinates: Coordinates) : Label
    }
}

class CurrentUserStoreFactory @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val storeFactory: StoreFactory
) {

    fun create(
        userId: Long
    ): CurrentUserStore =
        object : CurrentUserStore, Store<Intent, State, Label> by storeFactory.create(
            name = "CurrentUserStore",
            initialState = State(
                state = State.UserState.Initial
            ),
            bootstrapper = BootstrapperImpl(
                userId = userId
            ),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data class StartLoading(val userId: Long) : Action
    }

    private sealed interface Msg {
        data class Loaded(val user: User) : Msg
    }

    private class BootstrapperImpl(val userId: Long) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            dispatch(Action.StartLoading(userId))
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.OnPhoneClicked -> {
                    val state = getState()
                    if (state.state is State.UserState.Loaded) {
                        publish(OnPhoneClicked(state.state.user.cell))
                    }
                }

                is Intent.OnEmailClicked -> {
                    val state = getState()
                    if (state.state is State.UserState.Loaded) {
                        publish(OnEmailClicked(state.state.user.email))
                    }
                }

                is Intent.OnCoordinatesClicked -> {
                    val state = getState()
                    if (state.state is State.UserState.Loaded) {
                        publish(OnCoordinatesClicked(state.state.user.location.coordinates))
                    }
                }

            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.StartLoading -> {
                    scope.launch {
                        val user = getCurrentUserUseCase(action.userId)
                        dispatch(Msg.Loaded(user))
                    }
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {

                is Msg.Loaded -> {
                    copy(
                        state = State.UserState.Loaded(msg.user)
                    )
                }
            }
    }
}
