package ru.mavrinvladislav.system_design.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ShiftColorScheme(
    // Background
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val backgroundTertiary: Color,
    val backgroundDisable: Color,

    // Border
    val borderExtraLight: Color,
    val borderLight: Color,
    val borderMedium: Color,

    // Text
    val textInvert: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textQuaternary: Color,
    val textError: Color,

    // Indicator
    val indicatorWhite: Color,
    val indicatorLight: Color,
    val indicatorMedium: Color,
    val indicatorNormal: Color,
    val indicatorError: Color,
    val indicatorAttention: Color,
    val indicatorPositive: Color,

    // Brand Pizza
    val brandPizzaPrimary: Color,
    val brandPizzaPressed: Color,
    val brandPizzaHover: Color,
    val brandPizzaExtraLight: Color,
    val brandPizzaDisabled: Color,
    val brandPizzaIndicatorFocused: Color,
    val brandPizzaIndicatorFocusedAlternative: Color
)

private val LightColorScheme = ShiftColorScheme(
    // Background
    backgroundPrimary = BG_Primary,
    backgroundSecondary = BG_Secondary,
    backgroundTertiary = BG_Tertiary,
    backgroundDisable = BG_Disable,

    // Border
    borderExtraLight = Border_ExtraLight,
    borderLight = Border_Light,
    borderMedium = Border_Medium,

    // Text
    textInvert = Text_Invert,
    textPrimary = Text_Primary,
    textSecondary = Text_Secondary,
    textTertiary = Text_Tertiary,
    textQuaternary = Text_Quartenery,
    textError = Text_Error,

    // Indicator
    indicatorWhite = Indicator_White,
    indicatorLight = Indicator_Light,
    indicatorMedium = Indicator_Medium,
    indicatorNormal = Indicator_Normal,
    indicatorError = Indicator_Error,
    indicatorAttention = Indicator_Attention,
    indicatorPositive = Indicator_Positive,

    // Brand Pizza
    brandPizzaPrimary = Pizza_Brand,
    brandPizzaPressed = Pizza_Pressed_Primary,
    brandPizzaHover = Pizza_Hover_Primary,
    brandPizzaExtraLight = Pizza_Brand_ExtraLight,
    brandPizzaDisabled = Pizza_Brand_Disabled,
    brandPizzaIndicatorFocused = Pizza_Indicator_Focused,
    brandPizzaIndicatorFocusedAlternative = Pizza_Indicator_Focused_alternative
)

private val DarkColorScheme = LightColorScheme.copy()

private val LocalColors = staticCompositionLocalOf<ShiftColorScheme> {
    LightColorScheme
}
private val LocalTypography = staticCompositionLocalOf<ShiftTypography> {
    ShiftTypographyStyles
}

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {

    val colors = if (!darkTheme) LightColorScheme
    else DarkColorScheme
    val typography = ShiftTypographyStyles

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        content = content
    )
}

object ShiftTheme {
    val colors: ShiftColorScheme
        @Composable @ReadOnlyComposable
        get() = LocalColors.current
    val typography: ShiftTypography
        @Composable @ReadOnlyComposable
        get() = LocalTypography.current
}