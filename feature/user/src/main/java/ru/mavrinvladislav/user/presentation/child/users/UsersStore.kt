package ru.mavrinvladislav.user.presentation.child.users

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import jakarta.inject.Inject
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.mavrinvladislav.user.domain.model.User
import ru.mavrinvladislav.user.domain.usecase.GetUsersUseCase
import ru.mavrinvladislav.user.presentation.child.users.UsersStore.Intent
import ru.mavrinvladislav.user.presentation.child.users.UsersStore.Label
import ru.mavrinvladislav.user.presentation.child.users.UsersStore.State

interface UsersStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data object LoadNextPage : Intent
    }

    data class State(
        val usersState: UsersState,
        val isNextPageLoading: Boolean,
        val page: Int,
    ) {
        sealed interface UsersState {
            data object Initial : UsersState

            data object FirstPageLoading : UsersState

            data class Loaded(val users: List<User>) : UsersState

            data object Error : UsersState
        }
    }

    sealed interface Label {
    }
}

class UsersStoreFactory @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val storeFactory: StoreFactory
) {
//Store типо вьюмодели, а на компоненте экран живёт
    fun create(): UsersStore =
        object : UsersStore, Store<Intent, State, Label> by storeFactory.create(
            name = "UsersStore",
            initialState = State(
                usersState = State.UsersState.Initial,
                isNextPageLoading = false,
                page = START_PAGE
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {

        data object StartLoading : Action
    }

    private sealed interface Msg {

        data object StartLoading : Msg
        data class FirstPageLoaded(val users: List<User>) : Msg
        data class NextPageLoaded(val users: List<User>, val nextPageNumber: Int) : Msg
        data object Error : Msg
        data object IncrementPage : Msg
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            dispatch(Action.StartLoading)
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.LoadNextPage -> loadNextPage(getState)
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                Action.StartLoading -> loadFirstPage(getState)
            }
        }

        private fun loadFirstPage(getState: () -> State) {
            val state = getState()
            if (state.usersState is State.UsersState.FirstPageLoading) return

            dispatch(Msg.StartLoading)

            scope.launch(SupervisorJob()) {
                try {
                    getUsersUseCase(START_PAGE).collect { users ->
                        dispatch(Msg.FirstPageLoaded(users))
                    }
                } catch (e: Exception) {
                    dispatch(Msg.Error)
                }
            }
        }

        private fun loadNextPage(getState: () -> State) {
            val state = getState()

            if (state.isNextPageLoading) return

            val nextPage = state.page + 1
            dispatch(Msg.IncrementPage) // пометим, что пошла загрузка

            scope.launch {
                try {
                    getUsersUseCase(nextPage).collect { users ->
                        dispatch(Msg.NextPageLoaded(users, nextPage))
                    }
                } catch (e: Exception) {
                    dispatch(Msg.Error)
                }
            }
        }
    }


    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.StartLoading -> copy(
                    usersState = State.UsersState.FirstPageLoading,
                    isNextPageLoading = false
                )

                is Msg.FirstPageLoaded -> copy(
                    usersState = State.UsersState.Loaded(msg.users),
                    isNextPageLoading = false,
                    page = START_PAGE
                )

                is Msg.IncrementPage -> copy(isNextPageLoading = true)

                is Msg.NextPageLoaded -> copy(
                    usersState = when (val current = usersState) {
                        is State.UsersState.Loaded -> State.UsersState.Loaded(current.users + msg.users)
                        else -> usersState
                    },
                    isNextPageLoading = false,
                    page = msg.nextPageNumber
                )

                is Msg.Error -> copy(
                    usersState = State.UsersState.Error,
                    isNextPageLoading = false
                )
            }
    }


    companion object {

        private const val START_PAGE = 1
    }
}
