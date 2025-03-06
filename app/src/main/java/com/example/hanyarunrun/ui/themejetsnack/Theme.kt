/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.hanyarunrun.ui.themejetsnack

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightColorPalette = JetsnackColors(
    brand = Color(0xFF0D47A1), // Biru tua
    brandSecondary = Color(0xFF1976D2), // Biru sedang
    uiBackground = Color(0xFFFFFFFF), // Putih
    uiBorder = Color(0xFFBBDEFB), // Biru muda
    uiFloated = Color(0xFF64B5F6), // Biru terang
    textSecondary = Color(0xFF1565C0), // Biru gelap
    textHelp = Color(0xFF2196F3), // Biru standar
    textInteractive = Color(0xFF0D47A1), // Biru tua
    textLink = Color(0xFF42A5F5), // Biru muda
    iconSecondary = Color(0xFF1E88E5), // Biru cerah
    iconInteractive = Color(0xFF0D47A1), // Biru tua
    iconInteractiveInactive = Color(0xFFBBDEFB), // Biru pastel
    error = Color(0xFFD32F2F), // Merah untuk error
    gradient6_1 = listOf(Color(0xFF64B5F6), Color(0xFF1976D2), Color(0xFF0D47A1)), // Gradasi biru
    gradient6_2 = listOf(Color(0xFF90CAF9), Color(0xFF42A5F5), Color(0xFF1E88E5)), // Gradasi biru muda
    gradient3_1 = listOf(Color(0xFF0D47A1), Color(0xFF1976D2), Color(0xFF42A5F5)), // Gradasi biru sedang
    gradient3_2 = listOf(Color(0xFF64B5F6), Color(0xFF90CAF9), Color(0xFFBBDEFB)), // Gradasi biru terang
    gradient2_1 = listOf(Color(0xFF1976D2), Color(0xFF0D47A1)), // Gradasi biru tua
    gradient2_2 = listOf(Color(0xFF42A5F5), Color(0xFF64B5F6)), // Gradasi biru muda
    gradient2_3 = listOf(Color(0xFF90CAF9), Color(0xFFBBDEFB)), // Gradasi biru pastel
    tornado1 = listOf(Color(0xFF0D47A1), Color(0xFF1976D2)), // Efek tornado biru
    isDark = false
)


private val DarkColorPalette = JetsnackColors(
    brand = Color(0xFF0D47A1), // Biru tua
    brandSecondary = Color(0xFF1976D2), // Biru sedang
    uiBackground = Color(0xFFFFFFFF), // Putih
    uiBorder = Color(0xFFBBDEFB), // Biru muda
    uiFloated = Color(0xFF64B5F6), // Biru terang
    textSecondary = Color(0xFF1565C0), // Biru gelap
    textHelp = Color(0xFF2196F3), // Biru standar
    textInteractive = Color(0xFF0D47A1), // Biru tua
    textLink = Color(0xFF42A5F5), // Biru muda
    iconSecondary = Color(0xFF1E88E5), // Biru cerah
    iconInteractive = Color(0xFF0D47A1), // Biru tua
    iconInteractiveInactive = Color(0xFFBBDEFB), // Biru pastel
    error = Color(0xFFD32F2F), // Merah untuk error
    gradient6_1 = listOf(Color(0xFF64B5F6), Color(0xFF1976D2), Color(0xFF0D47A1)), // Gradasi biru
    gradient6_2 = listOf(Color(0xFF90CAF9), Color(0xFF42A5F5), Color(0xFF1E88E5)), // Gradasi biru muda
    gradient3_1 = listOf(Color(0xFF0D47A1), Color(0xFF1976D2), Color(0xFF42A5F5)), // Gradasi biru sedang
    gradient3_2 = listOf(Color(0xFF64B5F6), Color(0xFF90CAF9), Color(0xFFBBDEFB)), // Gradasi biru terang
    gradient2_1 = listOf(Color(0xFF1976D2), Color(0xFF0D47A1)), // Gradasi biru tua
    gradient2_2 = listOf(Color(0xFF42A5F5), Color(0xFF64B5F6)), // Gradasi biru muda
    gradient2_3 = listOf(Color(0xFF90CAF9), Color(0xFFBBDEFB)), // Gradasi biru pastel
    tornado1 = listOf(Color(0xFF0D47A1), Color(0xFF1976D2)), // Efek tornado biru
    isDark = true
)

@Composable
fun JetsnackTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    ProvideJetsnackColors(colors) {
        MaterialTheme(
            colorScheme = debugColors(darkTheme),
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

object JetsnackTheme {
    val colors: JetsnackColors
        @Composable
        get() = LocalJetsnackColors.current
}

/**
 * Jetsnack custom Color Palette
 */
@Immutable
data class JetsnackColors(
    val gradient6_1: List<Color> = listOf(Color(0xFF6200EA), Color(0xFF3700B3)),
    val gradient6_2: List<Color> = listOf(Color(0xFF03DAC5), Color(0xFF018786)),
    val gradient3_1: List<Color> = listOf(Color(0xFFFF5722), Color(0xFFF4511E), Color(0xFFE64A19)),
    val gradient3_2: List<Color> = listOf(Color(0xFF4CAF50), Color(0xFF388E3C), Color(0xFF2E7D32)),
    val gradient2_1: List<Color> = listOf(Color(0xFFBB86FC), Color(0xFF3700B3)),
    val gradient2_2: List<Color> = listOf(Color(0xFF03DAC5), Color(0xFF018786)),
    val gradient2_3: List<Color> = listOf(Color(0xFFFFC107), Color(0xFFFF9800)),
    val brand: Color = Color(0xFFFF5722),
    val brandSecondary: Color = Color(0xFF4CAF50),
    val uiBackground: Color = Color(0xFF121212),
    val uiBorder: Color = Color(0xFF757575),
    val uiFloated: Color = Color(0xFF1E1E1E),
    val interactivePrimary: List<Color> = gradient2_1,
    val interactiveSecondary: List<Color> = gradient2_2,
    val interactiveMask: List<Color> = gradient6_1,
    val textPrimary: Color = brand,
    val textSecondary: Color = Color(0xFFB0BEC5),
    val textHelp: Color = Color(0xFF90A4AE),
    val textInteractive: Color = Color(0xFF03DAC5),
    val textLink: Color = Color(0xFFBB86FC),
    val tornado1: List<Color> = listOf(Color(0xFFFFEB3B), Color(0xFFFFC107), Color(0xFFFF9800)),
    val iconPrimary: Color = brand,
    val iconSecondary: Color = Color(0xFF90A4AE),
    val iconInteractive: Color = Color(0xFF03DAC5),
    val iconInteractiveInactive: Color = Color(0xFF757575),
    val error: Color = Color(0xFFB00020),
    val notificationBadge: Color = error,
    val isDark: Boolean = true
)


@Composable
fun ProvideJetsnackColors(
    colors: JetsnackColors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalJetsnackColors provides colors, content = content)
}

private val LocalJetsnackColors = staticCompositionLocalOf<JetsnackColors> {
    error("No JetsnackColorPalette provided")
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colorScheme] in preference to [JetsnackTheme.colors].
 */
fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Magenta
) = ColorScheme(
    primary = debugColor,
    onPrimary = debugColor,
    primaryContainer = debugColor,
    onPrimaryContainer = debugColor,
    inversePrimary = debugColor,
    secondary = debugColor,
    onSecondary = debugColor,
    secondaryContainer = debugColor,
    onSecondaryContainer = debugColor,
    tertiary = debugColor,
    onTertiary = debugColor,
    tertiaryContainer = debugColor,
    onTertiaryContainer = debugColor,
    background = debugColor,
    onBackground = debugColor,
    surface = debugColor,
    onSurface = debugColor,
    surfaceVariant = debugColor,
    onSurfaceVariant = debugColor,
    surfaceTint = debugColor,
    inverseSurface = debugColor,
    inverseOnSurface = debugColor,
    error = debugColor,
    onError = debugColor,
    errorContainer = debugColor,
    onErrorContainer = debugColor,
    outline = debugColor,
    outlineVariant = debugColor,
    scrim = debugColor,
//    surfaceBright = debugColor,
//    surfaceDim = debugColor,
//    surfaceContainer = debugColor,
//    surfaceContainerHigh = debugColor,
//    surfaceContainerHighest = debugColor,
//    surfaceContainerLow = debugColor,
//    surfaceContainerLowest = debugColor,
)