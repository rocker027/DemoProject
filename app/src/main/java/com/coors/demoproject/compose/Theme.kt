package com.coors.demoproject.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.res.colorResource
import com.coors.demoproject.R


val DarkColorPalette = darkColors(
    primary = Teal200,
    primaryVariant = Teal700,
    onPrimary = Color.Black,
    secondary = Purple500,
    secondaryVariant = Purple700,
    onSecondary = Color.White
)

val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    onPrimary = Color.White,
    secondary = Teal200,
    secondaryVariant = Teal700,
    onSecondary = Color.Black
)

@Composable
fun BasicsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}