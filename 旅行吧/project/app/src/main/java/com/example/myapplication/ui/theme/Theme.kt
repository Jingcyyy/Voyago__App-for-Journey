package com.example.myapplication.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

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
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

data class ThemeColor(val first: Color = Color.White, val second: Color = Color.White, val third: Color = Color.White, val fourth: Color = Color.White)

// 定义主题实例
val BlueTheme = ThemeColor(
    first = Color(0xFF3A96A8),
    second = Color(0xFF033345),
    third = Color(0xFF7683D6),
    fourth = Color(0xFF3D82AC)
)
val LandTheme = ThemeColor(
    first = Color(0xFFFBFFB9),
    second = Color(0xFFFDD692),
    third = Color(0xFFEC7357),
    fourth = Color(0xFF754F44)
)
val OrangeTheme = ThemeColor(
    first = Color(0xFFF7AA97),
    second = Color(0xFFED9282),
    third = Color(0xFFDE7E73),
    fourth = Color(0xFFCFAA9E)
)
val GreenTheme = ThemeColor(
    first = Color(0xFFcff09e),
    second = Color(0xFFa8dba8),
    third = Color(0xFF79bd9a),
    fourth = Color(0xFF3b8686)
)