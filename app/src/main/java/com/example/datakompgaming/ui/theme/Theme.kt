package com.example.datakompgaming.ui.theme

import android.bluetooth.BluetoothA2dp
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorPalette = darkColorScheme(
    primary = BlueGreyMain80,
    onPrimary = BlueGreyMain20,
    primaryContainer = BlueGreyMain30,
    onPrimaryContainer = BlueGreyMain90,
    inversePrimary = BlueGreyMain40,

    secondary = DarkBlueGrey80,
    onSecondary = DarkBlueGrey20,
    secondaryContainer = DarkBlueGrey30,
    onSecondaryContainer = DarkBlueGrey90,

    tertiary = Violet80,
    onTertiary = Violet20,
    tertiaryContainer = Violet30,
    onTertiaryContainer = Violet90,

    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,

    background = Grey10,
    onBackground = Grey20,

    surface = BlueGreyGrey30,
    onSurface = BlueGreyGrey80,

    inverseSurface = Grey90,
    inverseOnSurface = Grey20,
    surfaceVariant = BlueGreyGrey30,
    onSurfaceVariant = BlueGreyGrey80,

    outline = BlueGreyGrey80
)

private val LightColorPalette = lightColorScheme(
    primary = BlueGreyMain40,
    onPrimary = Color.White,
    primaryContainer = BlueGreyMain90,
    onPrimaryContainer = BlueGreyMain10,
    inversePrimary = BlueGreyMain80,

    secondary = DarkBlueGrey40,
    onSecondary = Color.White,
    secondaryContainer = DarkBlueGrey90,
    onSecondaryContainer = DarkBlueGrey10,

    tertiary = Violet40,
    onTertiary = Color.White,
    tertiaryContainer = Violet90,
    onTertiaryContainer = Violet10,

    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,

    background = Grey99,
    onBackground = Grey10,

    surface = BlueGreyGrey90,
    onSurface = BlueGreyGrey30,

    inverseSurface = Grey20,
    inverseOnSurface = Grey95,
    surfaceVariant = BlueGreyGrey90,
    onSurfaceVariant = BlueGreyGrey30,

    outline = BlueGreyGrey40



)

@Composable
fun DataKompGamingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val useDynamicColors = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when
    {
        useDynamicColors && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        useDynamicColors && !darkTheme -> dynamicLightColorScheme(LocalContext.current)

        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }


    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}