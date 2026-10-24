package app.shell.fluid

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * Keeps a private copy of the wallpaper the user picked, so glass and later the lock
 * screen can draw it without storage permissions (PLAN.md, wallpaper decision).
 */
class WallpaperStore(private val context: Context) {

    private val file = File(context.filesDir, "wallpaper")

    suspend fun load(): ImageBitmap? = withContext(Dispatchers.IO) {
        if (file.exists()) decode() else null
    }

    /** Copies the picked image and decodes it. Returns null if it cannot be read. */
    suspend fun save(uri: Uri): ImageBitmap? = withContext(Dispatchers.IO) {
        try {
            val input = context.contentResolver.openInputStream(uri) ?: return@withContext null
            input.use { source -> file.outputStream().use { source.copyTo(it) } }
        } catch (e: IOException) {
            return@withContext null
        }
        decode()
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) { file.delete() }
    }

    /** Decodes at most at screen size; a wallpaper never needs more pixels than that. */
    private fun decode(): ImageBitmap? {
        val metrics = context.resources.displayMetrics
        return try {
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(file)) { decoder, info, _ ->
                val scale = max(
                    metrics.widthPixels.toFloat() / info.size.width,
                    metrics.heightPixels.toFloat() / info.size.height,
                ).coerceAtMost(1f)
                decoder.setTargetSize(
                    (info.size.width * scale).roundToInt().coerceAtLeast(1),
                    (info.size.height * scale).roundToInt().coerceAtLeast(1),
                )
            }.asImageBitmap()
        } catch (e: IOException) {
            null
        }
    }
}
