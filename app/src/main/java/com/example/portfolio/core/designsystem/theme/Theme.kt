package com.example.portfolio.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Dark22, //default button
    onPrimary = Gray73, //on button
    surface = Dark6, //full main background
    onSurface = Gray73, //full main on background
    surfaceContainer = Dark7,
    surfaceVariant = Dark13,
    outline = Dark33,
    secondary = Dark19,
    onSecondary = Dark7B
    /* primary = Purple80,
     secondary = PurpleGrey80,
      tertiary = Pink80
      */
)
private val LightColorScheme = lightColorScheme(
    primary = White90,
    onPrimary = Dark22,
    surface = White98,
    onSurface = Dark22,
    surfaceContainer = White90,
    surfaceVariant = GrayED,
    outline = Gray80,
    secondary = GrayEC,
    onSecondary = Dark84
    /* Other default colors to override
        primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun PortfolioTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialExpressiveTheme(
        colorScheme = colorScheme,
        typography = typography(),
        content = content
    )
}