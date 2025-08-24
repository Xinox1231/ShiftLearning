package ru.mavrinvladislav.user.ui.child.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import ru.mavrinvladislav.system_design.ui.compose.ActionIcon
import ru.mavrinvladislav.system_design.ui.compose.BrandText
import ru.mavrinvladislav.system_design.ui.compose.CustomAppBar
import ru.mavrinvladislav.system_design.ui.compose.Error
import ru.mavrinvladislav.system_design.ui.compose.ShimmerBox
import ru.mavrinvladislav.system_design.ui.compose.TextStyle
import ru.mavrinvladislav.system_design.ui.theme.ShiftTheme
import ru.mavrinvladislav.user.domain.model.Coordinates
import ru.mavrinvladislav.user.domain.model.DateOfBirth
import ru.mavrinvladislav.user.domain.model.Location
import ru.mavrinvladislav.user.domain.model.Name
import ru.mavrinvladislav.user.domain.model.Picture
import ru.mavrinvladislav.user.domain.model.Registration
import ru.mavrinvladislav.user.domain.model.Street
import ru.mavrinvladislav.user.domain.model.Timezone
import ru.mavrinvladislav.user.domain.model.User
import ru.mavrinvladislav.user.presentation.child.users.UsersComponent
import ru.mavrinvladislav.user.presentation.child.users.UsersStore
import ru.mavrinvladislav.user.ui.mapper.toUIModel
import ru.mavrinvladislav.user.ui.model.UserUIModel
import ru.mavrinvladislav.system_design.R as DesignR

@Composable
fun UsersContent(component: UsersComponent) {

    Scaffold(
        topBar = {
            CustomAppBar(
                text = "Random users",
                actions = listOf(
                    ActionIcon(
                        iconResId = DesignR.drawable.ic_reload,
                        onClick = { component.loadNewUsers() }
                    )
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            val model by component.model.collectAsState()

            when (val state = model.usersState) {
                is UsersStore.State.UsersState.Error -> Error(
                    onTryAgain = { component.reloadPage() }
                )

                is UsersStore.State.UsersState.Loading -> LoadingContent()

                is UsersStore.State.UsersState.Initial -> Unit

                is UsersStore.State.UsersState.Loaded -> LoadedContent(
                    state = state,
                    onClick = { component.clickOnUser(it) }
                )
            }
        }
    }
}

@Composable
private fun LoadingContent() {
    LazyColumn(
        contentPadding = PaddingValues(
            all = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp
        )
    ) {
        items(10) {
            UserCardSkeleton()
        }
    }
}

@Composable
@Preview
private fun UserCardSkeleton() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = ShiftTheme.colors.backgroundSecondary
        ),
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
        ) {
            ShimmerBox(
                modifier = Modifier.size(size = 120.dp),
                shape = CircleShape
            )

            Spacer(
                modifier = Modifier.size(20.dp)
            )

            Column{
                ShimmerBox(
                    modifier = Modifier
                        .height(height = 18.dp)
                        .fillMaxWidth(fraction = 0.7f)
                )

                Spacer(modifier = Modifier.size(20.dp))

                ShimmerBox(
                    modifier = Modifier
                        .height(height = 14.dp)
                        .fillMaxWidth(fraction = 0.8f)
                )

                Spacer(modifier = Modifier.size(32.dp))

                ShimmerBox(
                    modifier = Modifier
                        .height(height = 14.dp)
                        .fillMaxWidth(fraction = 0.34f)
                )
            }
        }
    }
}

@Composable
private fun LoadedContent(
    state: UsersStore.State.UsersState.Loaded,
    onClick: (Long) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(
            all = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp
        )
    ) {
        items(state.users) { user ->
            UserCard(
                user = user.toUIModel(),
                onClick = { onClick(user.id) }
            )
        }
    }
}

@Composable
private fun UserCard(
    modifier: Modifier = Modifier,
    user: UserUIModel,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = ShiftTheme.colors.backgroundSecondary
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
        ) {

            UserPicture(user.picture)

            Spacer(
                modifier = Modifier.size(20.dp)
            )

            UserInfo(user)
        }
    }
}

@Composable
private fun UserInfo(user: UserUIModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        BrandText(
            text = user.fullName,
            textStyle = TextStyle.MEDIUM_16
        )

        BrandText(
            text = user.address,
            textStyle = TextStyle.REGULAR_14
        )

        BrandText(
            text = user.cell,
            textStyle = TextStyle.REGULAR_14
        )
    }
}

@Composable
private fun UserPicture(imageUrl: String) {
    SubcomposeAsyncImage(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape),
        model = imageUrl,
        contentDescription = null,
        loading = {
            ShimmerBox(
                modifier = Modifier.fillMaxSize(),
                shape = CircleShape
            )
        }
    )
}

@Preview
@Composable
private fun UserCardPreview() {
    val user = User(
        id = 1L,
        gender = "female",
        name = Name(title = "Monsieur", first = "George", last = "Le Gall"),
        location = Location(
            street = Street(
                number = 3436,
                name = "Rue de LAbbé-Patureau"
            ),
            city = "Chamoson",
            state = "Jura",
            country = "Switzerland",
            postcode = "9077",
            coordinates = Coordinates(
                latitude = "-59.2588",
                longitude = "-159.6839"
            ),
            timezone = Timezone(offset = "+3:30", description = "Tehran")
        ),
        email = "george.legall@example.com",
        dateOfBirth = DateOfBirth(
            date = "1982 - 09 - 22 T19 :03:17.787 Z",
            age = "42"
        ),
        registered = Registration(
            date = "2020 - 10 - 13 T18 :46:49.428Z",
            yearsSinceRegistered = 4
        ),
        phone = "0771263422",
        cell = "0777206787",
        picture = Picture(
            large = "https://randomuser.me/api/portraits/men/60.jpg",
            medium = "https://randomuser.me/api/portraits/med/men/60.jpg",
            thumbnail = "https://randomuser.me/api/portraits/thumb/men/60.jpg"
        ), nat = "CH"
    )
    UserCard(
        user = user.toUIModel(),
        onClick = {}
    )
}