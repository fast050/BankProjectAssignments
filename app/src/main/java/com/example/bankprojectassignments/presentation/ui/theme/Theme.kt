package com.example.bankprojectassignments.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors


private val DarkColorScheme = darkColors(
    primary = Purple80,
    secondary = PurpleGrey80,
    surface = Pink80
)

private val LightColorScheme = lightColors(
    primary = Purple40,
    secondary = PurpleGrey40,
    surface = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun BankProjectAssignmentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colors = colorScheme,
        typography = Typography,
        content = content
    )
}