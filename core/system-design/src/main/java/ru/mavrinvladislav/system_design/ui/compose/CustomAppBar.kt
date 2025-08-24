package ru.mavrinvladislav.system_design.ui.compose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ru.mavrinvladislav.system_design.ui.theme.ShiftTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    text: String? = null,
    navigationIcon: ActionIcon? = null,
    actions: List<ActionIcon>? = null
) {
    TopAppBar(
        title = {
            text?.let {
                BrandText(
                    text = it,
                    textStyle = TextStyle.TITLE_H2
                )
            }
        },
        navigationIcon = {
            navigationIcon?.let {
                IconButton(
                    onClick = it.onClick
                ) {
                    Icon(
                        painter = painterResource(navigationIcon.iconResId),
                        contentDescription = navigationIcon.contentDescription
                    )
                }
            }
        },
        actions = {
            actions?.let { actionIcons ->
                actionIcons.forEach {
                    IconButton(
                        onClick = it.onClick
                    ) {
                        Icon(
                            painter = painterResource(it.iconResId),
                            contentDescription = it.contentDescription
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ShiftTheme.colors.backgroundPrimary
        )
    )
}