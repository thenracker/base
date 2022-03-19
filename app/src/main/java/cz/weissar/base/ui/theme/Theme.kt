package cz.weissar.base.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.White,
    primaryVariant = Blue50,
    secondary = DamiGreen,
    background = Gray800,
    surface = Gray900,
    onPrimary = Gray900,
    onSecondary = Gray900,
    onBackground = Color.White,
    onSurface = Color.White,
)

private val LightColorPalette = lightColors(

    primary = BaliBlueDark,
    primaryVariant = BaliBlue,
    secondary = DamiGreenDark,

    // Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun ExampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette
        else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}