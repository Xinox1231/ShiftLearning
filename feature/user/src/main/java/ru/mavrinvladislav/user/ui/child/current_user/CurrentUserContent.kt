package ru.mavrinvladislav.user.ui.child.current_user

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import ru.mavrinvladislav.system_design.ui.compose.ActionIcon
import ru.mavrinvladislav.system_design.ui.compose.BrandText
import ru.mavrinvladislav.system_design.ui.compose.CustomAppBar
import ru.mavrinvladislav.system_design.ui.compose.CustomTextButton
import ru.mavrinvladislav.system_design.ui.compose.ShimmerBox
import ru.mavrinvladislav.system_design.ui.compose.TextStyle
import ru.mavrinvladislav.user.presentation.child.current_user.CurrentUserComponent
import ru.mavrinvladislav.user.presentation.child.current_user.CurrentUserEvent
import ru.mavrinvladislav.user.presentation.child.current_user.CurrentUserStore
import ru.mavrinvladislav.user.ui.mapper.toUIModel
import ru.mavrinvladislav.user.ui.model.UserUIModel
import ru.mavrinvladislav.system_design.R as DesignR
import androidx.core.net.toUri

@Composable
internal fun CurrentUserContent(
    component: CurrentUserComponent
) {

    val model by component.model.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(
        component.event
    ) {
        component.event.collect {
            when (it) {
                is CurrentUserEvent.OpenDialer -> openDialer(
                    context = context,
                    phone = it.phone
                )
            }
        }

    }

    Scaffold(
        topBar = {
            CustomAppBar(
                navigationIcon = ActionIcon(
                    iconResId = DesignR.drawable.ic_arrow_back,
                    onClick = { component.onClickBack() }
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = model.state) {
                is CurrentUserStore.State.UserState.Initial -> Unit

                is CurrentUserStore.State.UserState.Loaded -> {

                    val user = state.user.toUIModel()

                    Column(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Spacer(modifier = Modifier.size(16.dp))

                        UserPicture(imageUrl = user.picture)

                        Spacer(modifier = Modifier.size(16.dp))

                        FullName(user.fullName)

                        DetailedInfo(
                            user = user,
                            modifier = Modifier.padding(16.dp),
                            onPhoneClick = { component.onPhoneClick(it) }
                        )
                    }
                }

                is CurrentUserStore.State.UserState.Loading -> {}
            }
        }
    }
}

private fun openDialer(context: Context, phone: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = "tel:${phone}".toUri()
    }
    context.startActivity(intent)
}

@Composable
private fun DetailedInfo(
    user: UserUIModel,
    modifier: Modifier = Modifier,
    onPhoneClick: (String) -> Unit,

    ) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BrandText(
            text = user.dateOfBirthAndAge,
            textStyle = TextStyle.REGULAR_16
        )

        CustomTextButton(
            text = user.cell,
            textStyle = TextStyle.REGULAR_16,
            onClick = { onPhoneClick(user.cell) }
        )

        BrandText(
            text = user.email,
            textStyle = TextStyle.REGULAR_16
        )

        BrandText(
            text = user.address,
            textStyle = TextStyle.REGULAR_16
        )

        BrandText(
            text = user.coordinates,
            textStyle = TextStyle.REGULAR_16
        )

        BrandText(
            text = user.nationality,
            textStyle = TextStyle.REGULAR_16
        )
    }
}

@Composable
private fun ColumnScope.FullName(fullName: String) {
    BrandText(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        text = fullName,
        textStyle = TextStyle.TITLE_H2,
    )
}

@Composable
private fun ColumnScope.UserPicture(imageUrl: String) {
    SubcomposeAsyncImage(
        modifier = Modifier
            .size(180.dp)
            .clip(CircleShape)
            .align(Alignment.CenterHorizontally),
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