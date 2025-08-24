package ru.mavrinvladislav.user.presentation.child.users

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.mavrinvladislav.user.domain.model.User
import ru.mavrinvladislav.user.domain.usecase.ClearUsersUseCase
import ru.mavrinvladislav.user.domain.usecase.GetUsersUseCase
import ru.mavrinvladislav.user.presentation.child.users.UsersStore.Intent
import ru.mavrinvladislav.user.presentation.child.users.UsersStore.Label
import ru.mavrinvladislav.user.presentation.child.users.UsersStore.State

interface UsersStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data object ReloadPage : Intent
        data object LoadNewUsers : Intent
    }

    data class State(
        val usersState: UsersState
    ) {
        sealed interface UsersState {
            data object Initial : UsersState
            data object Loading : UsersState
            data class Loaded(val users: List<User>) : UsersState
            data object Error : UsersState
        }
    }

    sealed interface Label
}

class UsersStoreFactory @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val clearUsersUseCase: ClearUsersUseCase,
    private val storeFactory: StoreFactory
) {
    fun create(): UsersStore =
        object : UsersStore, Store<Intent, State, Label> by storeFactory.create(
            name = "UsersStore",
            initialState = State(
                usersState = State.UsersState.Initial,
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data object StartLoading : Action
    }

    private sealed interface Msg {
        data object Loading : Msg
        data class Loaded(val users: List<User>) : Msg
        data object Error : Msg
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            dispatch(Action.StartLoading)
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {

        private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            dispatch(Msg.Error)
        }

        private var loadUsersJob: Job? = null

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                Action.StartLoading -> {
                    loadUsers(getState)
                }
            }
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.LoadNewUsers -> loadNewUsers(getState)
                is Intent.ReloadPage -> loadUsers(getState)

            }
        }

        private fun loadNewUsers(getState: () -> State) {
            scope.launch {
                clearUsersUseCase()
                loadUsers(getState)
            }
        }

        private fun loadUsers(getState: () -> State) {
            val currentState = getState()
            if (currentState is State.UsersState.Loading) return
            loadUsersJob?.cancel()
            loadUsersJob = scope.launch(exceptionHandler + SupervisorJob()) {
                dispatch(Msg.Loading)
                getUsersUseCase().collect {
                    dispatch(Msg.Loaded(it))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when (msg) {
            is Msg.Loading -> {
                copy(
                    usersState = State.UsersState.Loading
                )
            }

            is Msg.Error -> {
                copy(
                    usersState = State.UsersState.Error
                )
            }

            is Msg.Loaded -> {
                copy(
                    usersState = State.UsersState.Loaded(
                        users = msg.users
                    )
                )
            }
        }
    }
}