package app.shell.fluid.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import app.shell.fluid.glass.GlassStyle

private val LocalShellColors = staticCompositionLocalOf { LightColors }
private val LocalShellTypography = staticCompositionLocalOf { DefaultTypography }
private val LocalPanelGlass = staticCompositionLocalOf { GlassPresets.panelLight }

/**
 * App theme. Material3 stays underneath for accessibility defaults (ripple, minimum touch
 * size); the look comes from the Shell tokens.
 */
@Composable
fun ShellTheme(dark: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalShellColors provides if (dark) DarkColors else LightColors,
        LocalShellTypography provides DefaultTypography,
        LocalPanelGlass provides if (dark) GlassPresets.panelDark else GlassPresets.panelLight,
    ) {
        MaterialTheme(
            colorScheme = if (dark) darkColorScheme() else lightColorScheme(),
            content = content,
        )
    }
}

object ShellTheme {
    val colors: ShellColors
        @Composable @ReadOnlyComposable get() = LocalShellColors.current

    val typography: ShellTypography
        @Composable @ReadOnlyComposable get() = LocalShellTypography.current

    val panelGlass: GlassStyle
        @Composable @ReadOnlyComposable get() = LocalPanelGlass.current
}
