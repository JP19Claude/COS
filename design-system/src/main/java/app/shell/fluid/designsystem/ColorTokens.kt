package app.shell.fluid.designsystem

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/** Color roles (DESIGN_SPEC.md §4.1) plus component colors used so far. */
@Immutable
data class ShellColors(
    val textPrimary: Color,
    val textSecondary: Color,
    val accent: Color,
    val onAccent: Color,
    val tileOff: Color,
    val onTileOff: Color,
    val sliderTrack: Color,
    val sliderFill: Color,
    val switchTrackOff: Color,
    val switchThumb: Color,
)

@Unmeasured("Platzhalter bis Referenz vorliegt")
val LightColors = ShellColors(
    textPrimary = Color(0xFF16181C),
    textSecondary = Color(0xFF5E636B),
    accent = Color(0xFF2F6BFF),
    onAccent = Color(0xFFFFFFFF),
    tileOff = Color(0x1F000000),
    onTileOff = Color(0xFF16181C),
    sliderTrack = Color(0x26000000),
    sliderFill = Color(0xFFFFFFFF),
    switchTrackOff = Color(0x29000000),
    switchThumb = Color(0xFFFFFFFF),
)

@Unmeasured("Platzhalter bis Referenz vorliegt")
val DarkColors = ShellColors(
    textPrimary = Color(0xFFF4F5F7),
    textSecondary = Color(0xFFA3A8B0),
    accent = Color(0xFF4C80FF),
    onAccent = Color(0xFFFFFFFF),
    tileOff = Color(0x29FFFFFF),
    onTileOff = Color(0xFFF4F5F7),
    sliderTrack = Color(0x29FFFFFF),
    sliderFill = Color(0xFFFFFFFF),
    switchTrackOff = Color(0x3DFFFFFF),
    switchThumb = Color(0xFFFFFFFF),
)
