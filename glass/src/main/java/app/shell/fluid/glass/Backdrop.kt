package app.shell.fluid.glass

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * What lies behind the glass: the wallpaper image, or a placeholder brush while none is set.
 *
 * The source ([backdropSource]) must fill the window, because [GlassSurface] positions the
 * blurred copy by its offset from the window root.
 */
@Stable
class Backdrop(private val placeholder: (Size) -> Brush) {

    var image: ImageBitmap? by mutableStateOf(null)

    /** Size of the area the backdrop covers (the window). Named apart from [DrawScope.size]. */
    internal var sourceSize: IntSize by mutableStateOf(IntSize.Zero)

    internal fun DrawScope.drawBackdrop() {
        val area = sourceSize
        if (area == IntSize.Zero) return
        val target = area.toSize()
        val bitmap = image
        if (bitmap == null) {
            drawRect(brush = placeholder(target), size = target)
            return
        }
        // Center crop, like a wallpaper.
        val scale = max(target.width / bitmap.width, target.height / bitmap.height)
        val srcWidth = (target.width / scale).roundToInt().coerceIn(1, bitmap.width)
        val srcHeight = (target.height / scale).roundToInt().coerceIn(1, bitmap.height)
        drawImage(
            image = bitmap,
            srcOffset = IntOffset((bitmap.width - srcWidth) / 2, (bitmap.height - srcHeight) / 2),
            srcSize = IntSize(srcWidth, srcHeight),
            dstSize = area,
        )
    }
}

/** Draws [backdrop] behind the content and records the window size it covers. */
fun Modifier.backdropSource(backdrop: Backdrop): Modifier =
    onSizeChanged { backdrop.sourceSize = it }
        .drawBehind { with(backdrop) { drawBackdrop() } }
