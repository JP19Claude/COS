package app.shell.fluid

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.shell.fluid.designsystem.ShellDimens
import app.shell.fluid.designsystem.ShellTheme
import app.shell.fluid.designsystem.components.ListRow
import app.shell.fluid.designsystem.components.PillSlider
import app.shell.fluid.designsystem.components.RoundTile
import app.shell.fluid.designsystem.components.ShellSwitch
import app.shell.fluid.glass.Backdrop
import app.shell.fluid.glass.GlassSurface
import app.shell.fluid.glass.backdropSource

/** Demo gallery of the components built so far, on top of the wallpaper. */
@Composable
fun GalleryScreen(
    backdrop: Backdrop,
    onPickWallpaper: () -> Unit,
    onResetWallpaper: () -> Unit,
) {
    val panelShape = RoundedCornerShape(ShellDimens.panelRadius)
    val glass = ShellTheme.panelGlass
    val colors = ShellTheme.colors

    Box(
        Modifier
            .fillMaxSize()
            .backdropSource(backdrop),
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .systemBarsPadding()
                .padding(ShellDimens.screenPadding),
            verticalArrangement = Arrangement.spacedBy(ShellDimens.sectionGap),
        ) {
            GlassSurface(backdrop, glass, panelShape, Modifier.fillMaxWidth()) {
                Column(Modifier.padding(ShellDimens.panelPadding)) {
                    Text(stringResource(R.string.gallery_title), style = ShellTheme.typography.headline, color = colors.textPrimary)
                    Text(stringResource(R.string.gallery_note), style = ShellTheme.typography.label, color = colors.textSecondary)
                }
            }

            GlassSurface(backdrop, glass, panelShape, Modifier.fillMaxWidth()) {
                ControlPanel(Modifier.padding(ShellDimens.panelPadding))
            }

            GlassSurface(backdrop, glass, panelShape, Modifier.fillMaxWidth()) {
                var demoSwitch by remember { mutableStateOf(true) }
                Column {
                    ListRow(
                        title = stringResource(R.string.wallpaper_pick),
                        summary = stringResource(R.string.wallpaper_pick_summary),
                        onClick = onPickWallpaper,
                    )
                    ListRow(
                        title = stringResource(R.string.wallpaper_reset),
                        summary = stringResource(R.string.wallpaper_reset_summary),
                        onClick = onResetWallpaper,
                    )
                    ListRow(
                        title = stringResource(R.string.demo_switch),
                        trailing = { ShellSwitch(checked = demoSwitch, onCheckedChange = { demoSwitch = it }) },
                    )
                }
            }
        }
    }
}

@Composable
private fun ControlPanel(modifier: Modifier = Modifier) {
    val tiles = remember { mutableStateListOf(true, false, true, false) }
    var brightness by remember { mutableFloatStateOf(0.6f) }
    var volume by remember { mutableFloatStateOf(0.35f) }
    val labels = listOf(
        stringResource(R.string.tile_location),
        stringResource(R.string.tile_sound),
        stringResource(R.string.tile_lock),
        stringResource(R.string.tile_favorite),
    )
    val icons = listOf(Icons.Rounded.LocationOn, Icons.Rounded.Notifications, Icons.Rounded.Lock, Icons.Rounded.Star)

    Column(modifier) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            tiles.forEachIndexed { index, checked ->
                RoundTile(
                    checked = checked,
                    onCheckedChange = { tiles[index] = it },
                    icon = icons[index],
                    label = labels[index],
                )
            }
        }
        Spacer(Modifier.height(ShellDimens.sectionGap))
        PillSlider(value = brightness, onValueChange = { brightness = it }, label = stringResource(R.string.slider_brightness))
        Spacer(Modifier.height(ShellDimens.sliderGap))
        PillSlider(value = volume, onValueChange = { volume = it }, label = stringResource(R.string.slider_volume))
    }
}
