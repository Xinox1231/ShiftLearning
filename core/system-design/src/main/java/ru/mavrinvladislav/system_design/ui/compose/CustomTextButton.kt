package ru.mavrinvladislav.system_design.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.mavrinvladislav.system_design.ui.theme.ShiftTheme

@Composable
fun CustomTextButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle,
    textColor: Color = ShiftTheme.colors.textPrimary,
    onClick: () -> Unit
) {
    BrandText(
        text = text,
        textStyle = textStyle,
        underline = true,
        color = if (enabled) textColor else ShiftTheme.colors.brandDisabled,
        modifier = modifier
            .clickable(enabled = enabled, onClick = onClick)
    )
}