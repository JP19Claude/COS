package app.shell.fluid.glass

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/**
 * Parameters of one glass material (DESIGN_SPEC.md §4.6).
 *
 * @property blurRadius backdrop blur, API 31+
 * @property tint color laid over the blurred backdrop
 * @property fallbackTint opaque-ish surface used instead of blur below API 31
 * @property edgeColor thin light edge around the surface
 * @property edgeWidth width of that edge
 */
@Immutable
data class GlassStyle(
    val blurRadius: Dp,
    val tint: Color,
    val fallbackTint: Color,
    val edgeColor: Color,
    val edgeWidth: Dp,
)
