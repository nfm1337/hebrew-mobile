package il.nfm.learnhebrew.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// ── Color bridge ─────────────────────────────────────────────────────────────

private fun AppColors.toLightColorScheme(): ColorScheme = lightColorScheme(
    primary = blue,
    onPrimary = blueInk,
    secondary = green,
    onSecondary = blueInk,
    tertiary = yellow,
    onTertiary = yellowInk,
    background = bg,
    onBackground = ink,
    surface = surface,
    onSurface = ink,
    surfaceVariant = surface2,
    onSurfaceVariant = ink2,
    outline = line2,
    outlineVariant = line,
    error = red,
    onError = surface,
)

private fun AppColors.toDarkColorScheme(): ColorScheme = darkColorScheme(
    primary = blue,
    onPrimary = blueInk,
    secondary = green,
    onSecondary = blueInk,
    tertiary = yellow,
    onTertiary = yellowInk,
    background = bg,
    onBackground = ink,
    surface = surface,
    onSurface = ink,
    surfaceVariant = surface2,
    onSurfaceVariant = ink2,
    outline = line2,
    outlineVariant = line,
    error = red,
    onError = surface,
)

// ── Theme entry point ─────────────────────────────────────────────────────────

@Composable
fun LearnHebrewTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val appColors = if (darkTheme) darkAppColors() else lightAppColors()
    val appTypography = appTypography()
    val colorScheme =
        if (darkTheme) appColors.toDarkColorScheme() else appColors.toLightColorScheme()

    // Sync status-bar icon tint with theme (edge-to-edge is enabled in MainActivity).
    val view = LocalView.current
    if (!view.isInEditMode) {
        LaunchedEffect(darkTheme) {
            val window = (view.context as? android.app.Activity)?.window ?: return@LaunchedEffect
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalAppColors provides appColors,
        LocalAppTypography provides appTypography,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = appTypography.toMaterialTypography(),
            shapes = AppShapes,
            content = content,
        )
    }
}
