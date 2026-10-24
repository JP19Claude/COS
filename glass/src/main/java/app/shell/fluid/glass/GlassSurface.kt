package app.shell.fluid.glass

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot

/**
 * A surface that shows a blurred, tinted copy of [backdrop] behind its content.
 *
 * Blur needs API 31 ([BlurEffect]). Below that the surface uses [GlassStyle.fallbackTint]
 * with the same shape and edge. The blurred copy is drawn in the draw phase, so moving
 * or scrolling the surface does not recompose it.
 */
@Composable
fun GlassSurface(
    backdrop: Backdrop,
    style: GlassStyle,
    shape: Shape,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    var origin by remember { mutableStateOf(Offset.Zero) }
    val blur = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    Box(
        modifier
            .onGloballyPositioned { origin = it.positionInRoot() }
            .clip(shape)
            .then(if (blur) Modifier else Modifier.background(style.fallbackTint))
            .border(style.edgeWidth, style.edgeColor, shape),
    ) {
        if (blur) {
            Spacer(
                Modifier
                    .matchParentSize()
                    .graphicsLayer {
                        val radius = style.blurRadius.toPx()
                        renderEffect = BlurEffect(radius, radius, TileMode.Clamp)
                    }
                    .drawBehind {
                        translate(-origin.x, -origin.y) {
                            with(backdrop) { drawBackdrop() }
                        }
                        drawRect(style.tint)
                    },
            )
        }
        content()
    }
}
