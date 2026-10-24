package app.shell.fluid.designsystem.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.setProgress
import androidx.compose.ui.tooling.preview.Preview
import app.shell.fluid.common.rubberBand
import app.shell.fluid.common.rubberBandSlope
import app.shell.fluid.designsystem.GestureTokens
import app.shell.fluid.designsystem.HapticTokens
import app.shell.fluid.designsystem.MotionTokens
import app.shell.fluid.designsystem.ShellDimens
import app.shell.fluid.designsystem.ShellTheme
import kotlinx.coroutines.launch
import kotlin.math.abs

/**
 * Pill-shaped slider as in the control center (DESIGN_SPEC.md §7 "Helligkeits-Slider").
 *
 * The value follows the finger. Past either end the pill stretches in the drag direction
 * with rubber-band resistance and springs back on release, taking over the finger's velocity.
 *
 * @param value current value in 0..1
 */
@Composable
fun PillSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    val colors = ShellTheme.colors
    val view = LocalView.current
    val scope = rememberCoroutineScope()
    val stretch = remember { Animatable(0f) }
    var widthPx by remember { mutableFloatStateOf(1f) }
    val currentValue by rememberUpdatedState(value)
    val currentOnValueChange by rememberUpdatedState(onValueChange)
    val limitPx = with(LocalDensity.current) { GestureTokens.rubberBandSliderLimit.toPx() }
    val stiffness = GestureTokens.RUBBER_BAND_SLIDER_STIFFNESS
    val shape = RoundedCornerShape(ShellDimens.sliderHeight / 2)

    Column(modifier) {
        Text(text = label, style = ShellTheme.typography.label, color = colors.textSecondary)
        Spacer(Modifier.height(ShellDimens.sliderLabelGap))
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(ShellDimens.sliderHeight)
                .onSizeChanged { widthPx = it.width.toFloat().coerceAtLeast(1f) }
                .semantics {
                    contentDescription = label
                    progressBarRangeInfo = ProgressBarRangeInfo(value, 0f..1f)
                    setProgress { target ->
                        currentOnValueChange(target.coerceIn(0f, 1f))
                        true
                    }
                }
                .graphicsLayer {
                    val s = stretch.value
                    scaleX = 1f + abs(s) / widthPx
                    transformOrigin = TransformOrigin(if (s < 0f) 1f else 0f, 0.5f)
                }
                .clip(shape)
                .drawBehind {
                    drawRect(colors.sliderTrack)
                    drawRect(colors.sliderFill, size = Size(size.width * currentValue, size.height))
                }
                .pointerInput(Unit) {
                    val tracker = VelocityTracker()
                    var raw = 0f
                    var wasAtEdge = false
                    detectHorizontalDragGestures(
                        onDragStart = {
                            scope.launch { stretch.stop() }
                            tracker.resetTracking()
                            raw = currentValue * widthPx
                            wasAtEdge = false
                        },
                        onHorizontalDrag = { change, dragAmount ->
                            tracker.addPosition(change.uptimeMillis, change.position)
                            raw += dragAmount
                            val clamped = raw.coerceIn(0f, widthPx)
                            currentOnValueChange(clamped / widthPx)
                            val atEdge = clamped == 0f || clamped == widthPx
                            if (atEdge && !wasAtEdge) view.performHapticFeedback(HapticTokens.SLIDER_EDGE)
                            wasAtEdge = atEdge
                            val stretched = rubberBand(raw - clamped, limitPx, stiffness)
                            scope.launch { stretch.snapTo(stretched) }
                        },
                        onDragEnd = {
                            val overscroll = raw - raw.coerceIn(0f, widthPx)
                            val velocity = tracker.calculateVelocity().x *
                                rubberBandSlope(overscroll, limitPx, stiffness)
                            scope.launch {
                                stretch.animateTo(0f, MotionTokens.sliderDrag.spec(), initialVelocity = velocity)
                            }
                        },
                        onDragCancel = {
                            scope.launch { stretch.animateTo(0f, MotionTokens.sliderDrag.spec()) }
                        },
                    )
                },
        )
    }
}

@Preview
@Composable
private fun PillSliderPreview() {
    ShellTheme {
        PillSlider(value = 0.6f, onValueChange = {}, label = "Helligkeit")
    }
}
