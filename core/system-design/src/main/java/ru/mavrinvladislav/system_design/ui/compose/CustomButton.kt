package ru.mavrinvladislav.system_design.ui.compose

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mavrinvladislav.system_design.ui.theme.ShiftTheme

@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textColor: Color = ShiftTheme.colors.textInvert,
    shape: Shape = RoundedCornerShape(16.dp),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .sizeIn(
                minHeight = 56.dp
            ),
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = ShiftTheme.colors.brandPrimary,
            contentColor = ShiftTheme.colors.textInvert,
            disabledContentColor = ShiftTheme.colors.brandDisabled,
            disabledContainerColor = ShiftTheme.colors.textInvert
        )
    ) {
        BrandText(
            text = text,
            textStyle = TextStyle.BUTTON,
            color = textColor
        )
    }
}

@Preview
@Composable
private fun BrandButtonPreview() {
    CustomButton(
        text = "Продолжить",
        onClick = {}
    )
}