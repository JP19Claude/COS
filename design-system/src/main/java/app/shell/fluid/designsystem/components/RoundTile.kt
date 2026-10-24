package app.shell.fluid.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import app.shell.fluid.designsystem.HapticTokens
import app.shell.fluid.designsystem.MotionTokens
import app.shell.fluid.designsystem.ShellDimens
import app.shell.fluid.designsystem.ShellTheme

/**
 * Round control-center tile (DESIGN_SPEC.md §7 "Kachel klein").
 * Pressing compresses the circle, toggling animates its color.
 */
@Composable
fun RoundTile(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
) {
    val colors = ShellTheme.colors
    val view = LocalView.current
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val scale = animateFloatAsState(
        targetValue = if (pressed) ShellDimens.TILE_PRESSED_SCALE else 1f,
        animationSpec = MotionTokens.tilePress.spec(),
        label = "tilePress",
    )
    val container = animateColorAsState(
        targetValue = if (checked) colors.accent else colors.tileOff,
        animationSpec = MotionTokens.tileToggle.spec(),
        label = "tileToggle",
    )
    val iconTint by animateColorAsState(
        targetValue = if (checked) colors.onAccent else colors.onTileOff,
        animationSpec = MotionTokens.tileToggle.spec(),
        label = "tileToggleIcon",
    )

    Column(
        modifier.toggleable(
            value = checked,
            interactionSource = interaction,
            indication = null,
            role = Role.Switch,
            onValueChange = {
                view.performHapticFeedback(HapticTokens.TOGGLE)
                onCheckedChange(it)
            },
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            Modifier
                .size(ShellDimens.tileSize)
                .graphicsLayer {
                    scaleX = scale.value
                    scaleY = scale.value
                }
                .drawBehind { drawCircle(container.value) },
            contentAlignment = Alignment.Center,
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = iconTint)
        }
        Spacer(Modifier.height(ShellDimens.tileLabelGap))
        Text(text = label, style = ShellTheme.typography.label, color = colors.textPrimary)
    }
}

@Preview
@Composable
private fun RoundTilePreview() {
    ShellTheme {
        Row {
            RoundTile(checked = true, onCheckedChange = {}, icon = Icons.Rounded.LocationOn, label = "An")
            RoundTile(checked = false, onCheckedChange = {}, icon = Icons.Rounded.Lock, label = "Aus")
        }
    }
}
