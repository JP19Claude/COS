package app.shell.fluid.designsystem

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.shell.fluid.glass.GlassStyle

/** Glass presets (DESIGN_SPEC.md §4.6). Only the panel exists so far. */
@Unmeasured("Platzhalter bis Referenz vorliegt")
object GlassPresets {
    val panelLight = GlassStyle(
        blurRadius = 30.dp,
        tint = Color(0x8CFFFFFF),
        fallbackTint = Color(0xE6F4F5F7),
        edgeColor = Color(0x80FFFFFF),
        edgeWidth = ShellDimens.glassEdge,
    )

    val panelDark = GlassStyle(
        blurRadius = 30.dp,
        tint = Color(0x661C1E22),
        fallbackTint = Color(0xE61C1E22),
        edgeColor = Color(0x33FFFFFF),
        edgeWidth = ShellDimens.glassEdge,
    )
}

/**
 * The app's own placeholder wallpaper. It is not taken from any OS. The user replaces it
 * with any image from the gallery (PLAN.md, wallpaper decision).
 */
object PlaceholderWallpaper {
    private val colors = listOf(Color(0xFF1B2A6B), Color(0xFF5A3D9A), Color(0xFFE08A5B))

    fun brush(size: Size): Brush =
        Brush.linearGradient(colors = colors, start = Offset.Zero, end = Offset(size.width, size.height))
}
