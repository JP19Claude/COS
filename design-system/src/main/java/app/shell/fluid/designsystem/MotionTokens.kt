package app.shell.fluid.designsystem

import android.view.HapticFeedbackConstants
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

/** A spring as named in MOTION_TOKENS.md, in Compose units. */
@Immutable
data class SpringToken(val stiffness: Float, val dampingRatio: Float) {
    fun <T> spec(): SpringSpec<T> = spring(dampingRatio = dampingRatio, stiffness = stiffness)
}

/** Motion tokens. Names match MOTION_TOKENS.md. */
@Unmeasured("Platzhalter bis Referenz vorliegt")
object MotionTokens {
    /** `tilePress`: tile scale while pressed. */
    val tilePress = SpringToken(stiffness = 800f, dampingRatio = 0.6f)

    /** `tileToggle`: tile color on/off. */
    val tileToggle = SpringToken(stiffness = 400f, dampingRatio = 1f)

    /** `sliderDrag`: slider stretch settling back after release. */
    val sliderDrag = SpringToken(stiffness = 500f, dampingRatio = 0.55f)

    /** `switchToggle`: switch thumb and track. */
    val switchToggle = SpringToken(stiffness = 500f, dampingRatio = 0.7f)
}

/** Gesture parameters. Names match the gesture table in MOTION_TOKENS.md. */
@Unmeasured("Platzhalter bis Referenz vorliegt")
object GestureTokens {
    /** `rubberBandSlider`: largest visible stretch past a slider end. */
    val rubberBandSliderLimit = 24.dp

    /** `rubberBandSlider`: slope at the slider end. */
    const val RUBBER_BAND_SLIDER_STIFFNESS = 0.5f
}

/** Haptics (DESIGN_SPEC.md §4.8). */
@Unmeasured("Platzhalter bis Referenz vorliegt")
object HapticTokens {
    /** Tile and switch on/off. */
    const val TOGGLE = HapticFeedbackConstants.CONTEXT_CLICK

    /** Slider reaches an end. */
    const val SLIDER_EDGE = HapticFeedbackConstants.CLOCK_TICK
}
