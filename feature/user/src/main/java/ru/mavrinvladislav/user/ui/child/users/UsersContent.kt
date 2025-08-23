package ru.mavrinvladislav.user.ui.child.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mavrinvladislav.user.presentation.child.users.UsersComponent
import ru.mavrinvladislav.user.presentation.child.users.UsersStore

@Composable
fun UsersContent(component: UsersComponent) {

    val model by component.model.collectAsState()

    when (val state = model.usersState) {
        is UsersStore.State.UsersState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Ошибка")
            }
        }

        is UsersStore.State.UsersState.FirstPageLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UsersStore.State.UsersState.Initial -> {

        }

        is UsersStore.State.UsersState.Loaded -> {
            LazyColumn {
                items(state.users) {
                    UserCard()
                }
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun UserCard() {

    Text("start")

    Spacer(modifier = Modifier.size(100.dp))

    Text("end")
}