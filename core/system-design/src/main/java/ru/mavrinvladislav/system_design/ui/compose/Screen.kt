package ru.mavrinvladislav.system_design.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mavrinvladislav.system_design.ui.theme.MainTheme
import ru.mavrinvladislav.system_design.ui.theme.ShiftTheme

@Composable
fun Screen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    MainTheme {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .systemBarsPadding()
                .imePadding(),
            color = ShiftTheme.colors.backgroundPrimary,
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                content()
            }
        }
    }
}