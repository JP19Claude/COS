package app.shell.fluid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import app.shell.fluid.designsystem.PlaceholderWallpaper
import app.shell.fluid.designsystem.ShellTheme
import app.shell.fluid.glass.Backdrop
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val store = WallpaperStore(applicationContext)

        setContent {
            ShellTheme {
                val backdrop = remember { Backdrop(placeholder = PlaceholderWallpaper::brush) }
                val scope = rememberCoroutineScope()
                LaunchedEffect(Unit) { backdrop.image = store.load() }

                val picker = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                    if (uri != null) scope.launch { store.save(uri)?.let { backdrop.image = it } }
                }

                GalleryScreen(
                    backdrop = backdrop,
                    onPickWallpaper = {
                        picker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    },
                    onResetWallpaper = {
                        scope.launch {
                            store.clear()
                            backdrop.image = null
                        }
                    },
                )
            }
        }
    }
}
