package ru.mavrinvladislav.user.presentation.child.current_user

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.launch
import ru.mavrinvladislav.user.domain.model.User
import ru.mavrinvladislav.user.domain.usecase.GetCurrentUserUseCase
import ru.mavrinvladislav.user.presentation.child.current_user.CurrentUserStore.Intent
import ru.mavrinvladislav.user.presentation.child.current_user.CurrentUserStore.Label
import ru.mavrinvladislav.user.presentation.child.current_user.CurrentUserStore.State
import javax.inject.Inject

interface CurrentUserStore : Store<Intent, State, Label> {

    sealed interface Intent {
    }

    data class State(
        val state: UserState
    ) {
        sealed interface UserState {
            data object Initial : UserState

            data object Loading : UserState

            data class Loaded(
                val user: User
            ) : UserState
        }
    }

    sealed interface Label {
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
        data object Loading : Msg
        data class Loaded(val user: User) : Msg
    }

    private class BootstrapperImpl(val userId: Long) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            dispatch(Action.StartLoading(userId))
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.StartLoading -> {
                    dispatch(Msg.Loading)
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
                is Msg.Loading -> {
                    copy(
                        state = State.UserState.Loading
                    )
                }

                is Msg.Loaded -> {
                    copy(
                        state = State.UserState.Loaded(msg.user)
                    )
                }
            }
    }
}
