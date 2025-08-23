package ru.mavrinvladislav.system_design.ui.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import ru.mavrinvladislav.system_design.ui.theme.ShiftTheme

@Composable
fun BrandText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = ShiftTheme.colors.textPrimary,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textStyle: TextStyle
) {
    when (textStyle) {
        TextStyle.TITLE_H2 -> {
            Text(
                text = text,
                modifier = modifier,
                color = color,
                textAlign = textAlign,
                maxLines = maxLines,
                overflow = overflow,
                style = ShiftTheme.typography.title_h2
            )
        }

        TextStyle.BUTTON -> {
            Text(
                text = text,
                modifier = modifier,
                color = color,
                textAlign = textAlign,
                maxLines = maxLines,
                overflow = overflow,
                style = ShiftTheme.typography.button
            )
        }

        TextStyle.REGULAR_16 -> {
            Text(
                text = text,
                modifier = modifier,
                color = color,
                textAlign = textAlign,
                maxLines = maxLines,
                overflow = overflow,
                style = ShiftTheme.typography.regular_16
            )
        }

        TextStyle.REGULAR_12 -> {
            Text(
                text = text,
                modifier = modifier,
                color = color,
                textAlign = textAlign,
                maxLines = maxLines,
                overflow = overflow,
                style = ShiftTheme.typography.regular_12
            )
        }

        TextStyle.REGULAR_14 -> {
            Text(
                text = text,
                modifier = modifier,
                color = color,
                textAlign = textAlign,
                maxLines = maxLines,
                overflow = overflow,
                style = ShiftTheme.typography.regular_14
            )
        }

        TextStyle.MEDIUM_14 -> {
            Text(
                text = text,
                modifier = modifier,
                color = color,
                textAlign = textAlign,
                maxLines = maxLines,
                overflow = overflow,
                style = ShiftTheme.typography.medium_14
            )
        }

        TextStyle.MEDIUM_16 -> {
            Text(
                text = text,
                modifier = modifier,
                color = color,
                textAlign = textAlign,
                maxLines = maxLines,
                overflow = overflow,
                style = ShiftTheme.typography.medium_16
            )

        }

        TextStyle.BOTTOM_BAR -> {
            Text(
                text = text,
                modifier = modifier,
                color = color,
                textAlign = textAlign,
                maxLines = maxLines,
                overflow = overflow,
                style = ShiftTheme.typography.bottom
            )
        }

    }
}

enum class TextStyle {
    TITLE_H2,
    REGULAR_12,
    REGULAR_14,
    REGULAR_16,
    MEDIUM_14,
    MEDIUM_16,
    BUTTON,
    BOTTOM_BAR
}